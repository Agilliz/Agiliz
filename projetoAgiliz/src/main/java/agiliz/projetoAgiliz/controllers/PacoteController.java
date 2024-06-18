package agiliz.projetoAgiliz.controllers;

import java.util.Optional;
import java.util.UUID;

import agiliz.projetoAgiliz.models.Pacote;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import agiliz.projetoAgiliz.dto.PacoteDTO;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.services.PacoteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacotes")
@CrossOrigin
@RequiredArgsConstructor
public class PacoteController {

    private final PacoteService pacoteService;
    
    @PostMapping
    public ResponseEntity<MensageriaService<Pacote>> cadastrar(
                @RequestBody @Valid PacoteDTO pacoteDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new MensageriaService<>(
                                "Pacote inserido com sucesso",
                                pacoteService.inserir(pacoteDTO),
                                HttpStatus.CREATED.value()
                        )
                );
    }

    @GetMapping
    public ResponseEntity<MensageriaService<Page<Pacote>>> listar(Pageable pageable) {
        var pacotes = pacoteService.listarTodos(pageable);

        if (pacotes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new MensageriaService<>(
                                "Pacotes",
                                pacotes,
                                HttpStatus.OK.value()
                        )
                );

    }

    @GetMapping("/{id}")
    public ResponseEntity<MensageriaService<Pacote>> listarPorId(@PathVariable UUID id) {
        Optional<Pacote> pacote = pacoteService.listarPorId(id);

        if (pacote.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new MensageriaService<>(
                                    "Pacote não encontrado",
                                    "No content",
                                    HttpStatus.NOT_FOUND.value()
                                )
                        );
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new MensageriaService<>(
                                "Pacote: ",
                                pacote.get(),
                                HttpStatus.OK.value()
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensageriaService<Pacote>> alterar(@RequestBody @Valid PacoteDTO pacoteDTO, @PathVariable UUID id) {
        Optional<Pacote> pacoteOpt = pacoteService.listarPorId(id);

        if (pacoteOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new MensageriaService<>(
                                    "Pacote não encontrado",
                                    "No content",
                                    HttpStatus.NOT_FOUND.value()
                                )
                        );
        }

        var pacote = pacoteOpt.get();
        BeanUtils.copyProperties(pacoteDTO, pacote);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new MensageriaService<>(
                                "Pacote atualizado com sucesso",
                                pacoteService.atualizar(pacote),
                                HttpStatus.OK.value()
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Optional<Pacote> pacoteOpt = pacoteService.listarPorId(id);

        if (pacoteOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        pacoteService.deletarPorId(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
