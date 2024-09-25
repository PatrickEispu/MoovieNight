package br.com.trainee.MoovieNight.adapter.input.conta.dto.response;

import java.time.LocalDate;

public record BibliotecaUsuarioDtoResponse(String filme,String idPedido ,LocalDate dataDevolucao, String status) {
}
