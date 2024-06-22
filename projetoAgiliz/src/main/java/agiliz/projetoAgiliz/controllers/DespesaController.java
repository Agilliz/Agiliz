package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.DespesaDTO;
import agiliz.projetoAgiliz.dto.DespesaResponse;
import agiliz.projetoAgiliz.models.Despesa;
import agiliz.projetoAgiliz.services.DespesaService;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/despesas")
@CrossOrigin
public class DespesaController {

    @Autowired
    private DespesaService despesaService;


    @PostMapping
    ResponseEntity<MensageriaService<DespesaResponse>> cadastrarDespesa(@RequestBody @Valid DespesaDTO dto){
        return status(HttpStatus.CREATED)
                .body(
                        new MensageriaService<>(
                                "Despesa cadastrada com sucesso",
                                new DespesaResponse(despesaService.inserir(dto))
                        )
                );
    }
}
