package br.com.trainee.MoovieNight.adapter.input.filme;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.PedidoDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeGeneroDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeNomeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.DevolverFilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeSinopseDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IfilmeController {

    @Operation(summary = "Criação de um filme para ser adicionado no banco de dados feita através da autenticação do token. Essa operação é restrita para administradores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível criar o filme"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public FilmeDtoResponse criarFilme(String token, FilmeDtoRequest filmeDtoRequest);

    @Operation(summary = "Devolução de um filme alugado por um cliente. Operação restrita para adminstradores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme devolvido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível devolver o filme"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<DevolverFilmeDtoResponse> devolverFilme(PedidoDtoRequest pedidoDtoRequest);

    @Operation(summary = "Retorna a sinopse do filme escolhido pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinopse retornada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível retornar a sinopse do filme"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<FilmeSinopseDtoResponse> getFilmeSinopse(FilmeNomeDtoRequest filmeNomeDtoRequest);

    @Operation(summary = "Retorna uma lista de todos o filmes armazenados no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes retornado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível retornar a lista de filmes"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<List<FilmeDtoResponse>> getFilme();

    @Operation(summary = "Retorna uma lista de filmes por gênero definido na requisção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes retornado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível retornar a lista de filmes"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<List<FilmeDtoResponse>> getFilmeGenero(FilmeGeneroDtoRequest filmeGeneroDtoRequest);

    @Operation(summary = "Retorna uma lista de todos o filmes através de uma busca por nome aproximado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes retornado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível retornar a lista de filmes"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<List<FilmeDtoResponse>> getFilmeNome(FilmeNomeDtoRequest filmeNomeDtoRequest);


}
