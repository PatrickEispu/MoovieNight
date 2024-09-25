package br.com.trainee.MoovieNight.domain.ports.input.conta;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.AlugarFilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaLoginDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.ContaDtoResponse;
import br.com.trainee.MoovieNight.domain.entity.PedidoEntity;

import java.util.List;

public interface IContaCommand {
 public ContaDtoResponse criarConta(ContaDtoRequest contaDtoRequest);

    String login(ContaLoginDtoRequest contaLoginDtoRequest);

    PedidoEntity alugarFilme(String token, AlugarFilmeDtoRequest alugarFilmeDtoRequest, int dias);

    List<BibliotecaUsuarioDtoResponse> getFilmesAlugado(String token);

    void checkEmailExiste(String email);


}
