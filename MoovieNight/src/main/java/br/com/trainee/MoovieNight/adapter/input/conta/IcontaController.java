package br.com.trainee.MoovieNight.adapter.input.conta;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.AlugarFilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaLoginDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.AlugarFilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.ContaDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.ContaLoginDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IcontaController {

    @Operation(summary = "Criação de uma conta CLIENTE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível criar a conta"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ContaDtoResponse criarConta(ContaDtoRequest contaDtoRequest);

    @Operation(summary = "Login de uma conta CLIENTE para retornar um token de autenticação jwt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login feito com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível realizar o login"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ContaLoginDtoResponse login(ContaLoginDtoRequest contaLoginDtoRequest);

    @Operation(summary = "Aluguel de um filme por 3 dias a partir da data atual através da autenticação feita pelo token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme alugado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível alugar o filme"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<AlugarFilmeDtoResponse> alugarFilme3(String token, AlugarFilmeDtoRequest alugarFilmeDtoRequest);

    @Operation(summary = "Aluguel de um filme por 5 dias a partir da data atual através da autenticação feita pelo token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme alugado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível alugar o filme"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<AlugarFilmeDtoResponse> alugarFilme5(String token, AlugarFilmeDtoRequest alugarFilmeDtoRequest);


    @Operation(summary = "Aluguel de um filme por 7 dias a partir da data atual através da autenticação feita pelo token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme alugado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível alugar o filme"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<AlugarFilmeDtoResponse> alugarFilme7(String token, AlugarFilmeDtoRequest alugarFilmeDtoRequest);

    @Operation(summary = "Retornar a lista de filmes alugados por um cliente através da autenticação feita pelo token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes alugado pelo cliente!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível retornar a lista de filmes alugados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<List<BibliotecaUsuarioDtoResponse>> filmesAlugados(String token);

}
