package br.com.trainee.MoovieNight.adapter.input.conta;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.AlugarFilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.AlugarFilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.BibliotecaUsuarioDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.ContaLoginDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.ContaDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.conta.dto.response.ContaLoginDtoResponse;
import br.com.trainee.MoovieNight.domain.entity.PedidoEntity;
import br.com.trainee.MoovieNight.domain.ports.input.conta.IContaCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/conta")
public class ContaController {
    @Autowired
    IContaCommand iContaCommand;

    @PostMapping("/create")
    public ContaDtoResponse criarConta(@RequestBody ContaDtoRequest contaDtoRequest) {
        return iContaCommand.criarConta(contaDtoRequest);
    }

    @PostMapping("/login")
    public ContaLoginDtoResponse login(@RequestBody ContaLoginDtoRequest contaLoginDtoRequest) {
        String token = iContaCommand.login(contaLoginDtoRequest);
        return new ContaLoginDtoResponse(token);
    }

    @PostMapping("/alugarFilme3")
    public ResponseEntity<AlugarFilmeDtoResponse> alugarFilme3(@RequestHeader("Authorization") String token, @RequestBody AlugarFilmeDtoRequest alugarFilmeDtoRequest) {
        int dias = 3;
        PedidoEntity pedido = iContaCommand.alugarFilme(token, alugarFilmeDtoRequest, dias);
        AlugarFilmeDtoResponse filmeResponse = new AlugarFilmeDtoResponse(
                "Filme alugado com sucesso!",
                pedido.getIdPedidoAluguel(),
                "Data de devolução: " + pedido.getFimAluguel());
        return new ResponseEntity<>(filmeResponse, HttpStatus.OK);
    }

    @PostMapping("/alugarFilme5")
    public ResponseEntity<AlugarFilmeDtoResponse> alugarFilme5(@RequestHeader("Authorization") String token, @RequestBody AlugarFilmeDtoRequest alugarFilmeDtoRequest) {
        int dias = 5;
        PedidoEntity pedido = iContaCommand.alugarFilme(token, alugarFilmeDtoRequest, dias);
        AlugarFilmeDtoResponse filmeResponse = new AlugarFilmeDtoResponse(
                "Filme alugado com sucesso!",
                pedido.getIdPedidoAluguel(),
                "Data de devolução: " + pedido.getFimAluguel());
        return new ResponseEntity<>(filmeResponse, HttpStatus.OK);
    }

    @PostMapping("/alugarFilme7")
    public ResponseEntity<AlugarFilmeDtoResponse> alugarFilme7(@RequestHeader("Authorization") String token, @RequestBody AlugarFilmeDtoRequest alugarFilmeDtoRequest) {
        int dias = 7;
        PedidoEntity pedido = iContaCommand.alugarFilme(token, alugarFilmeDtoRequest, dias);
        AlugarFilmeDtoResponse filmeResponse = new AlugarFilmeDtoResponse(
                "Filme alugado com sucesso!",
                pedido.getIdPedidoAluguel(),
                "Data de devolução: " + pedido.getFimAluguel());
        return new ResponseEntity<>(filmeResponse, HttpStatus.OK);
    }

    @GetMapping("/getBiblioteca")
    public ResponseEntity<List<BibliotecaUsuarioDtoResponse>> filmesAlugados(@RequestHeader("Authorization") String token) {
        List<BibliotecaUsuarioDtoResponse> filmesList = iContaCommand.getFilmesAlugado(token);
        return new ResponseEntity<>(filmesList, HttpStatus.OK);
    }


}
