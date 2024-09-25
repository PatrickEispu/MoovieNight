package br.com.trainee.MoovieNight.domain.entity;

import br.com.trainee.MoovieNight.domain.enuns.FilmeGenero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmeEntity {
    private int idFilme;
    private String nome;
    private FilmeGenero filmeGenero;
    private int classIndicativa;
    private int quantidade;

    public FilmeEntity(String nome, FilmeGenero filmeGenero, int classIndicativa, int quantidade) {
        this.nome = nome;
        this.filmeGenero = filmeGenero;
        this.classIndicativa = classIndicativa;
        this.quantidade = quantidade;
    }

}
