package br.com.trainee.MoovieNight.util.dto;

import br.com.trainee.MoovieNight.domain.enuns.TipoConta;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JwtDto {
    private int id;
    private String idCartao;
    private String nome;
    private String email;
    private TipoConta tipoConta;
    private String dataNascimento;
}
