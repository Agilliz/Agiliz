package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.tipoColaborador.TipoColaboradorRequest;
import agiliz.projetoAgiliz.dto.tipoColaborador.TipoColaboradorResponse;
import agiliz.projetoAgiliz.models.TipoColaborador;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.services.TipoColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-colaborador")
@CrossOrigin
public class TipoColaboradorController {

    @Autowired
    private TipoColaboradorService tipoColaboradorService;

    @PostMapping
    public ResponseEntity<MensageriaService<TipoColaborador>> cadastrar(
            @RequestBody @Valid TipoColaboradorRequest dto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
          new MensageriaService<>(
                  "Tipo de colaborador cadastrado",
                  tipoColaboradorService.inserir(dto),
                  HttpStatus.CREATED.value()
          )
        );
    }

    @GetMapping
    public ResponseEntity<MensageriaService<List<TipoColaboradorResponse>>> listar(){
        var tiposColaborador = tipoColaboradorService.listar();

        if(tiposColaborador.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.status(HttpStatus.OK).body(
                new MensageriaService<List<TipoColaboradorResponse>>(
                        "Tipos colaboradores: ",
                        tiposColaborador,
                        HttpStatus.OK.value()
                )
        );
    }
}
