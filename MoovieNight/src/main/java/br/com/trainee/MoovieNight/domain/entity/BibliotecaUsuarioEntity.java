package br.com.trainee.MoovieNight.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaUsuarioEntity {
    private String idPedidoAluguel;
    private int idConta;
    private String filme;

}
