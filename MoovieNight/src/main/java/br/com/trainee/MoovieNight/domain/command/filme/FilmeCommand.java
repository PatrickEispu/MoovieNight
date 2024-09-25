package br.com.trainee.MoovieNight.domain.command.filme;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.PedidoDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeGeneroDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeNomeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeSinopseDtoResponse;
import br.com.trainee.MoovieNight.domain.constante.ErrorMessage;
import br.com.trainee.MoovieNight.domain.entity.FilmeEntity;
import br.com.trainee.MoovieNight.domain.enuns.AluguelStatus;
import br.com.trainee.MoovieNight.domain.enuns.FilmeGenero;
import br.com.trainee.MoovieNight.domain.exception.*;
import br.com.trainee.MoovieNight.domain.ports.input.filme.IfilmeCommand;
import br.com.trainee.MoovieNight.domain.ports.output.filme.IfilmeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeCommand implements IfilmeCommand {
    @Autowired
    IfilmeRespository iFilmeRespository;


    @Override
    public FilmeDtoResponse criarFilme(FilmeDtoRequest filmeDtoRequest) {
        //verificacao da requesst
        verificaFilmeRequest(filmeDtoRequest);

        String nome = filmeDtoRequest.nome();
        String sinopse = filmeDtoRequest.sinopse();
        FilmeGenero genero = FilmeGenero.fromString(filmeDtoRequest.filmeGenero());

        int classIndicativa = filmeDtoRequest.classIndicativa();
        int quantidade = filmeDtoRequest.quantidade();


        FilmeEntity filme = new FilmeEntity(
                nome,
                genero,
                classIndicativa,
                quantidade
        );
        try {
            iFilmeRespository.criarFilme(filme, sinopse);
        } catch (NegocioException e) {
            throw new NegocioException(ErrorMessage.FALHA_SALVAR_FILME);
        }

        return new FilmeDtoResponse(nome, genero, classIndicativa, quantidade);

    }

    @Override
    public int getFilmeId(String filme) {
        if (filmeExiste(filme)) {
            return iFilmeRespository.getFilmeId(filme);
        } else {
            throw new NegocioException(ErrorMessage.FILME_NAO_ENCONTRADO);
        }
    }

    @Override
    public List<BibliotecaUsuarioDtoResponse> getFilmeAlugado(int id) {
        return iFilmeRespository.getFilmeAlugado(id);
    }

    @Override
    public void putFilme(PedidoDtoRequest pedidoDtoRequest) {
        checkPedidoRequest(pedidoDtoRequest);

        String idPedido = pedidoDtoRequest.idPedido();

        checkPedidoExiste(idPedido, pedidoDtoRequest.email());
        checkPedidoJaFinalizado(idPedido, pedidoDtoRequest.email());

        AluguelStatus aluguelStatus = AluguelStatus.ENCERRADO;
        iFilmeRespository.putFilme(idPedido, aluguelStatus);

    }

    private void checkPedidoJaFinalizado(String idPedido, String email) {
        if (iFilmeRespository.checkPedidoFinalizado(idPedido, email)) {
            throw new NegocioException(ErrorMessage.PEDIDO_JA_FINALIZADO);
        }
    }

    public void checkPedidoExiste(String idPedido, String email) {
        if (!iFilmeRespository.checkPedidoExiste(idPedido, email)) {
            throw new NegocioException(ErrorMessage.PEDIDO_NAO_EXISTENTE);
        }

    }

    private void checkPedidoRequest(PedidoDtoRequest pedidoDtoRequest) {
        if (pedidoDtoRequest.idPedido() == null || pedidoDtoRequest.idPedido().isEmpty()) {
            throw new NegocioException(ErrorMessage.PEDIDO_INVALIDO);
        }
        if (pedidoDtoRequest.email() == null || pedidoDtoRequest.email().isEmpty()) {
            throw new NegocioException(ErrorMessage.EMAIL_INVALIDO);
        }
        if (!checkEmailCadastrado(pedidoDtoRequest.email())) {
            throw new NegocioException(ErrorMessage.EMAIL_NAO_ENCONTRADO);
        }
    }

    private boolean checkEmailCadastrado(String email) {
        return iFilmeRespository.CheckEmailCastrado(email);
    }

    @Override
    public FilmeSinopseDtoResponse getFilmeSinopse(FilmeNomeDtoRequest filmeNomeDtoRequest) {
        if (!filmeExiste(filmeNomeDtoRequest.filme())) {
            throw new NegocioException(ErrorMessage.FILME_NAO_ENCONTRADO);
        }

        return iFilmeRespository.getFilmeSinopse(filmeNomeDtoRequest.filme());
    }

    @Override
    public List<FilmeDtoResponse> getFilme() {
        return iFilmeRespository.getFilme();
    }

    @Override
    public List<FilmeDtoResponse> getFilmeGenero(FilmeGeneroDtoRequest filmeGeneroDtoRequest) {
        String genero = filmeGeneroDtoRequest.filmeGenero().getFilmeGenero();
        return iFilmeRespository.getFilmeGenero(genero);
    }

    @Override
    public List<FilmeDtoResponse> getFilmeNome(FilmeNomeDtoRequest filmeNomeDtoRequest) {
        String nome = filmeNomeDtoRequest.filme();
        return iFilmeRespository.getFilmeNome(nome);
    }

    public boolean filmeExiste(String filme) {
        return iFilmeRespository.filmeExiste(filme);
    }

    @Override
    public void checkFilmeQuantidade(String filme) {
        if (!iFilmeRespository.checkFilmeQuantidade(filme)) {
            throw new NegocioException(ErrorMessage.FILME_INDISPONIVEL);
        }
    }

    @Override
    public void checkPedidoRealizado(String email, String idPedido) {
        if (iFilmeRespository.checkPedidoRealizado(email, idPedido)) {
            throw new NegocioException(ErrorMessage.PEDIDO_JA_REALIZADO);
        }
    }


    private void verificaFilmeRequest(FilmeDtoRequest filmeDtoRequest) {
        if (filmeDtoRequest.nome() == null || filmeDtoRequest.nome().isEmpty())
            throw new NegocioException(ErrorMessage.FILME_NOME_INVALIDO);

        if (filmeDtoRequest.filmeGenero() == null || filmeDtoRequest.filmeGenero().isEmpty())
            throw new NegocioException(ErrorMessage.FILME_GENERO_INVALIDO);

        if (filmeDtoRequest.classIndicativa() > 18 || filmeDtoRequest.classIndicativa() < 0)
            throw new NegocioException(ErrorMessage.FILME_CLASS_INDICATIVA_INVALIDA);

        if (filmeDtoRequest.quantidade() < 0)
            throw new NegocioException(ErrorMessage.FILME_QUANTIDADE_INVALIDA);

    }


}
