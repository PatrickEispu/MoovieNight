package br.com.trainee.MoovieNight.domain.command.conta;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.AlugarFilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaLoginDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.ContaDtoResponse;
import br.com.trainee.MoovieNight.domain.constante.ErrorMessage;
import br.com.trainee.MoovieNight.domain.entity.ContaEntity;
import br.com.trainee.MoovieNight.domain.entity.FilmeEntity;
import br.com.trainee.MoovieNight.domain.entity.PedidoEntity;
import br.com.trainee.MoovieNight.domain.enuns.AluguelStatus;
import br.com.trainee.MoovieNight.domain.enuns.TipoConta;
import br.com.trainee.MoovieNight.domain.exception.NegocioException;
import br.com.trainee.MoovieNight.domain.ports.input.conta.IContaCommand;
import br.com.trainee.MoovieNight.domain.ports.input.filme.IfilmeCommand;
import br.com.trainee.MoovieNight.domain.ports.output.conta.IcontaRepository;
import br.com.trainee.MoovieNight.util.DataUtil;
import br.com.trainee.MoovieNight.util.EmailUtil;
import br.com.trainee.MoovieNight.util.JwtUtil;
import br.com.trainee.MoovieNight.util.dto.JwtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ContaCommand implements IContaCommand {
    @Autowired
    IcontaRepository iContaRepository;
    @Autowired
    IfilmeCommand ifilmeCommand;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    DataUtil dataUtil;



    public ContaDtoResponse criarConta(ContaDtoRequest contaDtoRequest) {
        checkContaRequest(contaDtoRequest);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(contaDtoRequest.dataNascimento(), dtf);

        String nome = contaDtoRequest.nome();
        String email = contaDtoRequest.email();

        String idCartao = gerarCartaoId(nome, dataNasc);

        ContaEntity conta = new ContaEntity(
                nome,
                email,
                dataNasc,
                idCartao,
                TipoConta.CLIENTE
        );

        iContaRepository.saveConta(conta);

        return new ContaDtoResponse(nome, idCartao);
    }

    private void checkContaRequest(ContaDtoRequest contaDtoRequest) {
        if (contaDtoRequest.nome() == null || contaDtoRequest.nome().isEmpty()) {
            throw new NegocioException(ErrorMessage.CONTA_NOME_INVALIDO);
        }
        if (contaDtoRequest.email() == null || contaDtoRequest.email().isEmpty() || !emailUtil.isValidEmail(contaDtoRequest.email())) {
            throw new NegocioException(ErrorMessage.EMAIL_INVALIDO);
        }
        if (iContaRepository.checkEmailExiste(contaDtoRequest.email()))
        {
            throw new NegocioException(ErrorMessage.EMAIL_JA_CADASTRADO);
        }

        if (
                contaDtoRequest.dataNascimento() == null || contaDtoRequest.dataNascimento().isEmpty())
            throw new NegocioException(ErrorMessage.IDADE_INVALIDA);
        else {
            if (dataUtil.calcIdade(contaDtoRequest.dataNascimento()) > 100 ||
                    dataUtil.calcIdade(contaDtoRequest.dataNascimento()) < 12)
                throw new NegocioException(ErrorMessage.IDADE_INVALIDA);
        }
    }

    @Override
    public String login(ContaLoginDtoRequest contaLoginDtoRequest) {
        String email = contaLoginDtoRequest.email();
        String idCartao = contaLoginDtoRequest.idCartao();
        //verifica se o email está cadastrado
        checkEmailExiste(email);

        if (checkLoginInfo(email, idCartao)) {
            ContaEntity conta = iContaRepository.buscarContaInfo(email, idCartao);

            return JwtUtil.generateToken(conta);
        } else {
            throw new NegocioException(ErrorMessage.FALHA_LOGIN);
        }

    }

    @Override
    public PedidoEntity alugarFilme(String token, AlugarFilmeDtoRequest alugarFilmeDtoRequest, int dias) {
        JwtDto jwtDto = JwtUtil.decodeToken(token);
        checkAlugarFilmeRequest(alugarFilmeDtoRequest);

        int idConta = jwtDto.getId();
        String idCartao = jwtDto.getIdCartao();
        String filme = alugarFilmeDtoRequest.nomeFilme();

        int idFilme = ifilmeCommand.getFilmeId(filme);
        checkIdadeUsuario(jwtDto.getDataNascimento(),idFilme);
        checkFilmeQuantidade(filme);

        LocalDate dataInicio = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.now().plusDays(dias);

        String idPedido = gerarPedidoId(idCartao, idFilme, dataInicio);
        checkPedidoRealizado(jwtDto.getEmail(),idPedido);

        PedidoEntity pedido = new PedidoEntity(
                idPedido,
                dataInicio,
                dataDevolucao,
                AluguelStatus.ATIVO
        );

        iContaRepository.alugarFilme(pedido,idConta,filme);

        return pedido;

    }

    private void checkPedidoRealizado(String email, String idPedido) {
        ifilmeCommand.checkPedidoRealizado(email,idPedido);
    }

    private void checkFilmeQuantidade(String filme) {
       ifilmeCommand.checkFilmeQuantidade(filme);
    }

    private void checkIdadeUsuario(String dataNascimento, int idFilme) {
        int idade = dataUtil.calcIdadeCliente(dataNascimento);
        FilmeEntity filme = iContaRepository.getFilmeInfo(idFilme);
        int classIndicativa = filme.getClassIndicativa();
        if (classIndicativa >idade)
        {
            throw new NegocioException(ErrorMessage.IDADE_NAO_AUTORIZADA);
        }
    }

    private void checkAlugarFilmeRequest(AlugarFilmeDtoRequest alugarFilmeDtoRequest) {
        if (alugarFilmeDtoRequest.nomeFilme() == null || alugarFilmeDtoRequest.nomeFilme().isEmpty())
        {
            throw new NegocioException(ErrorMessage.FILME_NOME_INVALIDO);
        }
        if (!ifilmeCommand.filmeExiste(alugarFilmeDtoRequest.nomeFilme()))
        {
            throw new NegocioException(ErrorMessage.FILME_NAO_ENCONTRADO);
        }
    }

    @Override
    public List<BibliotecaUsuarioDtoResponse> getFilmesAlugado(String token) {
       JwtDto jwtDto = JwtUtil.decodeToken(token);

        return ifilmeCommand.getFilmeAlugado(jwtDto.getId());
    }

    private String gerarPedidoId(String idCartao, int idFilme, LocalDate dataInicio) {
        String alg1 = String.valueOf(idFilme);
        String alg2 = dataInicio.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String alg3 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));

        return idCartao + alg1 + alg2+alg3;
    }

    private boolean checkLoginInfo(String email, String idCartao) {
        return iContaRepository.checkLoginIndo(email, idCartao);
    }

    public void checkEmailExiste(String email) {
        if (!iContaRepository.checkEmailExiste(email)) {
            throw new NegocioException(ErrorMessage.EMAIL_NAO_ENCONTRADO);
        }

    }




    private String gerarCartaoId(String nome, LocalDate dataNasc) {
        String cartaoIdA = nome.substring(0, 3).toUpperCase();

        String dia = dataNasc.format(DateTimeFormatter.ofPattern("dd"));
        String mes = dataNasc.format(DateTimeFormatter.ofPattern("MM"));
        String cartaoIdB = String.valueOf(dia + mes);

        return cartaoIdA + cartaoIdB;
    }

}
