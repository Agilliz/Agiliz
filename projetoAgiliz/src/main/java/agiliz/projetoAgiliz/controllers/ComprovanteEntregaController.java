package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.comprovanteEntrega.ComprovanteEntregaRequest;
import agiliz.projetoAgiliz.dto.comprovanteEntrega.ComprovanteEntregaResponse;
import agiliz.projetoAgiliz.services.ComprovanteEntregaService;
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
@RequestMapping("/comprovantes-entrega")
@RequiredArgsConstructor
public class ComprovanteEntregaController {

    private final ComprovanteEntregaService service;

    @PostMapping
    public ResponseEntity<MensageriaService<ComprovanteEntregaResponse>> cadastrar(@RequestBody @Valid ComprovanteEntregaRequest dto) {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<ComprovanteEntregaResponse>()
                                .mensagemCliente("Comprovante cadastrado")
                                .data(new ComprovanteEntregaResponse(service.cadastrar(dto)))
                                .status(200)
                );
    }

    @GetMapping
    public ResponseEntity<MensageriaService<List<ComprovanteEntregaResponse>>> getAll() {
        List<ComprovanteEntregaResponse> comprovantes = service.getAllResponse();

        if(comprovantes.isEmpty()) return status(HttpStatus.NO_CONTENT).build();

        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<List<ComprovanteEntregaResponse>>()
                                .mensagemCliente("Comprovantes:")
                                .data(comprovantes)
                                .status(200)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensageriaService<ComprovanteEntregaResponse>> getPorId(@PathVariable UUID id) {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<ComprovanteEntregaResponse>()
                                .mensagemCliente("Comprovante:")
                                .data(new ComprovanteEntregaResponse(service.getPorId(id)))
                                .status(200)
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensageriaService<ComprovanteEntregaResponse>> alterar(
            @RequestBody @Valid ComprovanteEntregaRequest dto,
            @PathVariable UUID id
    ) {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<ComprovanteEntregaResponse>()
                                .mensagemCliente("Comprovante alterado com sucesso")
                                .data(new ComprovanteEntregaResponse(service.alterar(dto, id)))
                                .status(200)
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarPorId(id);
        return status(HttpStatus.NO_CONTENT).build();
    }
}
