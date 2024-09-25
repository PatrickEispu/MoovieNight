package br.com.trainee.MoovieNight.adapter.input.filme;

import br.com.trainee.MoovieNight.adapter.input.conta.dto.request.PedidoDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeGeneroDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.request.FilmeNomeDtoRequest;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.DevolverFilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeDtoResponse;
import br.com.trainee.MoovieNight.adapter.input.filme.dto.response.FilmeSinopseDtoResponse;
import br.com.trainee.MoovieNight.domain.ports.input.filme.IfilmeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/filme")
@RestController
public class FilmeController implements IfilmeController {
    @Autowired
    IfilmeCommand iFilmeCommand;


    @PostMapping("/adm/createFilme")
    public FilmeDtoResponse criarFilme(@RequestHeader("Authorization") String token, @RequestBody FilmeDtoRequest filmeDtoRequest) {
        return iFilmeCommand.criarFilme(filmeDtoRequest);
    }

    @PutMapping("/adm/putFilme")
      public ResponseEntity<DevolverFilmeDtoResponse> devolverFilme(
                @RequestBody PedidoDtoRequest pedidoDtoRequest) {
            iFilmeCommand.putFilme(pedidoDtoRequest);
        DevolverFilmeDtoResponse devolverFilmeDtoResponse = new DevolverFilmeDtoResponse("Pedido finalizado com sucesso!");
        return new ResponseEntity<>(devolverFilmeDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/getFilmeSinopse")
    public ResponseEntity<FilmeSinopseDtoResponse> getFilmeSinopse(@RequestBody FilmeNomeDtoRequest filmeNomeDtoRequest) {
        FilmeSinopseDtoResponse filmeSinopseDtoResponse = iFilmeCommand.getFilmeSinopse(filmeNomeDtoRequest);
        return new ResponseEntity<>(filmeSinopseDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/getFilme")
    public ResponseEntity<List<FilmeDtoResponse>> getFilme() {
        List<FilmeDtoResponse> filmeList = iFilmeCommand.getFilme();
        return new ResponseEntity<>(filmeList, HttpStatus.OK);
    }

    @GetMapping("/getFilmeGenero")
    public ResponseEntity<List<FilmeDtoResponse>> getFilmeGenero(@RequestBody FilmeGeneroDtoRequest filmeGeneroDtoRequest) {
        List<FilmeDtoResponse> filmeList = iFilmeCommand.getFilmeGenero(filmeGeneroDtoRequest);
        return new ResponseEntity<>(filmeList, HttpStatus.OK);
    }

    @GetMapping("/getFilmeNome")
    public ResponseEntity<List<FilmeDtoResponse>> getFilmeNome(@RequestBody FilmeNomeDtoRequest filmeNomeDtoRequest) {
        List<FilmeDtoResponse> filmeList = iFilmeCommand.getFilmeNome(filmeNomeDtoRequest);
        return new ResponseEntity<>(filmeList, HttpStatus.OK);
    }

}
