package br.com.trainee.MoovieNight.adapter.input.filme.dto.request;

import br.com.trainee.MoovieNight.domain.enuns.FilmeGenero;

public record FilmeDtoRequest(String nome, String filmeGenero, int classIndicativa, int quantidade,String sinopse) {
}
