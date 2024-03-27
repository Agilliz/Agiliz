package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.VigenciaDTO;
import agiliz.projetoAgiliz.models.Vigencia;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.services.VigenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vigencia")
public class VigenciaController {
    @Autowired
    private VigenciaService vigenciaService;

    @PostMapping
    public ResponseEntity<MensageriaService<Vigencia>> inserir(@RequestBody @Valid VigenciaDTO vigenciaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MensageriaService<>(
                        "Vigência ",
                        vigenciaService.inserir(vigenciaDTO),
                        HttpStatus.CREATED.value()
                )
        );
    }

    @GetMapping
    public ResponseEntity<MensageriaService<List<Vigencia>>> buscar(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new MensageriaService<List<Vigencia>>(
                        "Vigências ",
                        vigenciaService.listar(),
                        HttpStatus.OK.value()
                )
            );
    }
}
