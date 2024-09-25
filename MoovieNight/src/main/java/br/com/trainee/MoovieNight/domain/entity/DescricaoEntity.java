package br.com.trainee.MoovieNight.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DescricaoEntity {
    private int idFilme;
    private String sinopse;
}
