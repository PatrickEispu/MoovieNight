package br.com.trainee.MoovieNight.domain.entity;

import br.com.trainee.MoovieNight.domain.enuns.AluguelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    private String idPedidoAluguel;
    private LocalDate inicioAluguel;
    private LocalDate fimAluguel;
    private AluguelStatus aluguelStatus;
}
