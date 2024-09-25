package br.com.trainee.MoovieNight.domain.ports.output.filme;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeSinopseDtoResponse;
import br.com.trainee.MoovieNight.domain.entity.FilmeEntity;
import br.com.trainee.MoovieNight.domain.enuns.AluguelStatus;

import java.util.List;

public interface IfilmeRespository {
    void criarFilme(FilmeEntity filme, String sinopse);

    int getFilmeId(String filme);

    boolean filmeExiste(String filme);

    List<BibliotecaUsuarioDtoResponse> getFilmeAlugado(int id);

    void putFilme(String idPedido, AluguelStatus aluguelStatus);

    FilmeSinopseDtoResponse getFilmeSinopse(String s);

    List<FilmeDtoResponse> getFilme();

    List<FilmeDtoResponse> getFilmeGenero(String genero);

    List<FilmeDtoResponse> getFilmeNome(String nome);

    boolean checkFilmeQuantidade(String filme);

    boolean checkPedidoRealizado(String email, String idPedido);

    boolean checkPedidoExiste(String idPedido, String email);

    boolean CheckEmailCastrado(String email);

    boolean checkPedidoFinalizado(String idPedido, String email);
}
