package br.com.trainee.MoovieNight.domain.entity;

import br.com.trainee.MoovieNight.domain.enuns.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaEntity {
    private int idConta;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String idCartao;
    private TipoConta tipoConta;


    public ContaEntity(String nome, String email, LocalDate dataNascimento, String idCartao, TipoConta tipoConta) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.idCartao = idCartao;
        this.tipoConta = tipoConta;
    }


}
