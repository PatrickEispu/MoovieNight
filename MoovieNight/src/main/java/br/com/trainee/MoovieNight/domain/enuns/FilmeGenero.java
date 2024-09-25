package br.com.trainee.MoovieNight.domain.enuns;

import br.com.trainee.MoovieNight.domain.constante.ErrorMessage;
import br.com.trainee.MoovieNight.domain.exception.NegocioException;

public enum FilmeGenero {
    ACAO("Acao"),
    COMEDIA("Comedia"),
    DRAMA("Drama"),
    TERROR("Terror"),
    ROMANCE("Romance"),
    ANIMACAO("Animacao");

    private String msg;

    FilmeGenero(String msg) {
        this.msg = msg;
    }

    public String getFilmeGenero() {
        return msg;
    }

    public static FilmeGenero fromString(String input) {
        for (FilmeGenero genero : FilmeGenero.values()) {
            if (genero.getFilmeGenero().equalsIgnoreCase(input.trim())) {
                return genero;
            }
        }
        throw new NegocioException(ErrorMessage.FILME_GENERO_INVALIDO);
    }
}
