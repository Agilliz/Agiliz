package agiliz.projetoAgiliz.controllers;

import java.util.Optional;
import java.util.UUID;

import agiliz.projetoAgiliz.dto.pacote.PacoteResponse;
import agiliz.projetoAgiliz.models.Pacote;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import agiliz.projetoAgiliz.dto.pacote.PacoteRequest;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.services.PacoteService;
import jakarta.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/pacotes")
@CrossOrigin
@RequiredArgsConstructor
public class PacoteController {

    private final PacoteService pacoteService;
    
    @PostMapping
    public ResponseEntity<MensageriaService<Pacote>> cadastrar(
                @RequestBody @Valid PacoteRequest pacoteRequest
    ) {
        return status(HttpStatus.CREATED)
                .body(
                        new MensageriaService<>(
                                "Pacote inserido com sucesso",
                                pacoteService.inserir(pacoteRequest),
                                HttpStatus.CREATED.value()
                        )
                );
    }

    @GetMapping
    public ResponseEntity<MensageriaService<Page<Pacote>>> listar(Pageable pageable) {
        var pacotes = pacoteService.listarTodos(pageable);

        if (pacotes.isEmpty()) return status(HttpStatus.NO_CONTENT).build();

        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<>(
                                "Pacotes",
                                pacotes,
                                HttpStatus.OK.value()
                        )
                );

    }

    @GetMapping("/{id}")
    public ResponseEntity<MensageriaService<PacoteResponse>> listarPorId(@PathVariable UUID id) {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<PacoteResponse>()
                                .mensagemCliente("Pacote: ")
                                .data(new PacoteResponse(pacoteService.listarPorId(id)))
                                .status(HttpStatus.OK.value())
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensageriaService<PacoteResponse>> alterar(@RequestBody @Valid PacoteRequest dto, @PathVariable UUID id) {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<PacoteResponse>()
                                .mensagemCliente("Pacote atualizado com sucesso")
                                .data(new PacoteResponse(pacoteService.atualizar(dto, id)))
                                .status(HttpStatus.OK.value())
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pacoteService.deletarPorId(id);
        return status(HttpStatus.ACCEPTED).build();
    }
}
