package br.com.trainee.MoovieNight.adapter.output.conta;

import br.com.trainee.MoovieNight.domain.entity.ContaEntity;
import br.com.trainee.MoovieNight.domain.entity.FilmeEntity;
import br.com.trainee.MoovieNight.domain.entity.PedidoEntity;
import br.com.trainee.MoovieNight.domain.enuns.FilmeGenero;
import br.com.trainee.MoovieNight.domain.enuns.TipoConta;
import br.com.trainee.MoovieNight.domain.ports.output.conta.IcontaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class ContaRepositoryImpl implements IcontaRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void saveConta(ContaEntity conta) {
        String sql = "CALL criar_conta(?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                conta.getNome(),
                conta.getEmail(),
                conta.getDataNascimento(),
                conta.getIdCartao(),
                conta.getTipoConta().getTipoConta()
        );

    }

    @Override
    public boolean checkEmailExiste(String email) {
        String sql = "select verifica_email(?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, boolean.class, email));
    }

    @Override
    public boolean checkLoginIndo(String email, String idCartao) {
        String sql = "SELECT verifica_login(?,?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, email, idCartao));
    }

    @Override
    public ContaEntity buscarContaInfo(String email, String idCartao) {
        String sql = "SELECT * FROM buscar_conta(?,?)";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                {
                    ContaEntity conta = new ContaEntity();
                    conta.setIdConta(rs.getInt("t_id_conta"));
                    conta.setEmail(rs.getString("t_email"));
                    conta.setTipoConta(TipoConta.valueOf(rs.getString("t_tipo_conta")));
                    conta.setIdCartao(rs.getString("t_id_cartao"));
                    conta.setDataNascimento(rs.getObject("t_data_nascimento", LocalDate.class));
                    return conta;
                }
                , email, idCartao);

    }

    @Override
    public void alugarFilme(PedidoEntity pedido, int idConta, String filme) {
        String sql = "CALL alugar_filme(" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?" +
                ")";
        jdbcTemplate.update(
                sql,
                pedido.getIdPedidoAluguel(),
                idConta,
                filme,
                pedido.getInicioAluguel(),
                pedido.getFimAluguel(),
                pedido.getAluguelStatus().getAluguelStatus()
        );
    }

    @Override
    public FilmeEntity getFilmeInfo(int idFilme) {
        String sql = "SELECT * FROM buscar_filme_por_id(?)";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
        {
            return new FilmeEntity(
                    rs.getString("t_filme"),
                    FilmeGenero.fromString(rs.getString("t_genero")),
                    rs.getInt("t_class_indicativa"),
                    rs.getInt("t_quantidade")
            );

        }, idFilme);
    }
}
