package br.com.trainee.MoovieNight.domain.ports.output.conta;

import br.com.trainee.MoovieNight.domain.entity.ContaEntity;
import br.com.trainee.MoovieNight.domain.entity.FilmeEntity;
import br.com.trainee.MoovieNight.domain.entity.PedidoEntity;

public interface IcontaRepository {
    void saveConta(ContaEntity conta);

    boolean checkEmailExiste(String email);

    boolean checkLoginIndo(String email,String idCartao);

    ContaEntity buscarContaInfo(String email, String idCartao);

    void alugarFilme(PedidoEntity pedido, int idConta, String filme);

    FilmeEntity getFilmeInfo(int idFilme);
}
