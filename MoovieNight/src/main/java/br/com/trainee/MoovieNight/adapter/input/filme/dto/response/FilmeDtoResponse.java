package br.com.trainee.MoovieNight.adapter.input.filme.dto.response;

import br.com.trainee.MoovieNight.domain.enuns.FilmeGenero;

public record FilmeDtoResponse(String nome, FilmeGenero filmeGenero, int classIndicativa, int quantidade) {
}
