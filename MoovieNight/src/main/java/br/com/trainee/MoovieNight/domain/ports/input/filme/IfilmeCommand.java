package br.com.trainee.MoovieNight.domain.ports.input.filme;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.PedidoDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeGeneroDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeNomeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeSinopseDtoResponse;

import java.util.List;

public interface IfilmeCommand {
    FilmeDtoResponse criarFilme(FilmeDtoRequest filmeDtoRequest);

    int getFilmeId(String filme);


    List<BibliotecaUsuarioDtoResponse> getFilmeAlugado(int id);

    void putFilme(PedidoDtoRequest pedidoDtoRequest);

    FilmeSinopseDtoResponse getFilmeSinopse(FilmeNomeDtoRequest filmeNomeDtoRequest);

    List<FilmeDtoResponse> getFilme();

    List<FilmeDtoResponse> getFilmeGenero(FilmeGeneroDtoRequest filmeGeneroDtoRequest);

    List<FilmeDtoResponse> getFilmeNome(FilmeNomeDtoRequest filmeNomeDtoRequest);

    boolean filmeExiste(String filme);

    void checkFilmeQuantidade(String filme);

    void checkPedidoRealizado(String email, String idPedido);
    void checkPedidoExiste(String idPedido, String email);
}
