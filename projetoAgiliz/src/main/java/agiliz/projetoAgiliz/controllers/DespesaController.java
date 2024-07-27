package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.despesa.DespesaRequest;
import agiliz.projetoAgiliz.dto.despesa.DespesaResponse;
import agiliz.projetoAgiliz.services.DespesaService;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/despesas")
@CrossOrigin
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService service;


    @PostMapping
    ResponseEntity<MensageriaService<? extends DespesaResponse>> cadastrar(@RequestBody @Valid DespesaRequest dto) {
        return status(HttpStatus.CREATED)
                .body(
                        new MensageriaService<DespesaResponse>()
                                .mensagemCliente("Despesa cadastrada com sucesso")
                                .data(new DespesaResponse(service.inserir(dto)))
                                .status(201)
                );
    }

    @GetMapping
    ResponseEntity<MensageriaService<List<DespesaResponse>>> getAll() {
        var despesasResponse = service.getAllResponse();

        if(despesasResponse.isEmpty()) status(HttpStatus.NO_CONTENT).build();

        return status(HttpStatus.OK)
                    .body(
                            new MensageriaService<List<DespesaResponse>>()
                                    .mensagemCliente("Despesas: ")
                                    .data(despesasResponse)
                    );
    }

    @GetMapping("/{id}")
    ResponseEntity<MensageriaService<DespesaResponse>> getPorId(@PathVariable UUID id) {
        return status(200)
                .body(
                        new MensageriaService<DespesaResponse>()
                                .mensagemCliente("Despesa por id")
                                .data(new DespesaResponse(service.getPorId(id)))
                                .status(200)
                );
    }

    @PutMapping("/{id}")
    ResponseEntity<MensageriaService<DespesaResponse>> alterar(@RequestBody @Valid DespesaRequest dto, @PathVariable UUID id) {
        return status(HttpStatus.OK).body(
                new MensageriaService<DespesaResponse>()
                        .mensagemCliente("Alterado com sucesso")
                        .data(new DespesaResponse(service.alterar(dto, id)))
                        .status(200)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MensageriaService<Void>> deletar(@PathVariable UUID id) {
        service.deletarPorId(id);
        return status(204).build();
    }
}
