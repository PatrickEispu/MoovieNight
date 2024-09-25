package br.com.trainee.MoovieNight.adapter.output.filme;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeSinopseDtoResponse;
import br.com.trainee.MoovieNight.domain.entity.FilmeEntity;
import br.com.trainee.MoovieNight.domain.enuns.AluguelStatus;
import br.com.trainee.MoovieNight.domain.enuns.FilmeGenero;
import br.com.trainee.MoovieNight.domain.ports.output.filme.IfilmeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FilmeRepository implements IfilmeRespository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void criarFilme(FilmeEntity filme, String sinopse) {
        String sql = "CALL criar_filme(?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                filme.getNome(),
                filme.getClassIndicativa(),
                filme.getFilmeGenero().getFilmeGenero(),
                filme.getQuantidade(),
                sinopse);

    }

    @Override
    public int getFilmeId(String filme) {
        String sql = "SELECT buscar_id_filme(?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, filme);
    }

    @Override
    public boolean filmeExiste(String filme) {
        String sql = "SELECT VERIFICA_FILME(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, filme);
    }

    @Override
    public List<BibliotecaUsuarioDtoResponse> getFilmeAlugado(int id) {
        String sql = "SELECT * FROM buscar_filme_alugado(?)";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                {
                    BibliotecaUsuarioDtoResponse bibliotecaUsuarioDtoResponse = new BibliotecaUsuarioDtoResponse(
                            rs.getString("t_filme"),
                            rs.getString("t_id_pedido"),
                            rs.getObject("t_data_devolucao", LocalDate.class),
                            rs.getString("t_status")
                    );
                    return bibliotecaUsuarioDtoResponse;

                }
                , id);
    }

    @Override
    public void putFilme(String idPedido, AluguelStatus aluguelStatus) {
        String sql = "CALL devolver_filme(?,?)";
        jdbcTemplate.update(sql, idPedido, aluguelStatus.getAluguelStatus());
    }

    @Override
    public FilmeSinopseDtoResponse getFilmeSinopse(String nome) {
        String sql = "SELECT buscar_filme_sinopse(?)";
        String sinopse = jdbcTemplate.queryForObject(sql, String.class, nome);
        return new FilmeSinopseDtoResponse(sinopse);
    }

    @Override
    public List<FilmeDtoResponse> getFilme() {
        String sql = "SELECT * FROM buscar_todo_filme()";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            return new FilmeDtoResponse(

                    rs.getString("t_nome"),
                    FilmeGenero.fromString(rs.getString("t_genero")),
                    rs.getInt("t_class_indicativa"),
                    rs.getInt("t_quantidade")
            );
        });

    }

    @Override
    public List<FilmeDtoResponse> getFilmeGenero(String genero) {
        String sql = "SELECT * FROM buscar_genero_filme(?)";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            return new FilmeDtoResponse(
                    rs.getString("t_nome"),
                    FilmeGenero.fromString(genero),
                    rs.getInt("t_class_indicativa"),
                    rs.getInt("t_quantidade")

            );
        }, genero);
    }

    @Override
    public List<FilmeDtoResponse> getFilmeNome(String nome) {
        String sql = "SELECT * FROM buscar_nome_filme(?)";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            return new FilmeDtoResponse(
                    rs.getString("t_nome"),
                    FilmeGenero.fromString(rs.getString( "t_genero")),
                    rs.getInt("t_class_indicativa"),
                    rs.getInt("t_quantidade")

            );
        }, nome);
    }

    @Override
    public boolean checkFilmeQuantidade(String filme) {
        String sql = "SELECT verifica_filme_quantidade(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class,filme);
    }

    @Override
    public boolean checkPedidoRealizado(String email, String idPedido) {
        String sql = "SELECT verifica_pedido_realizado(?,?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class,email,idPedido);
    }

    @Override
    public boolean checkPedidoExiste(String idPedido, String email) {
        String sql = "SELECT verifica_pedido_existe(?,?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class,email,idPedido);

    }

    @Override
    public boolean CheckEmailCastrado(String email) {
        String sql = "SELECT verifica_email(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class,email);
    }

    @Override
    public boolean checkPedidoFinalizado(String idPedido, String email) {
        String sql ="SELECT verifica_pedido_encerrado(?,?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class,email,idPedido);
    }
}
