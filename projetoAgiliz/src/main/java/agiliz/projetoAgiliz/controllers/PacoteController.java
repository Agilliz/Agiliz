package agiliz.projetoAgiliz.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agiliz.projetoAgiliz.dto.PacoteDTO;
import agiliz.projetoAgiliz.models.PacoteModel;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacote")
public class PacoteController {

    @Autowired
    private IPacoteRepository pacoteRepository;

    @Autowired
    

    @PostMapping
    public ResponseEntity<MensageriaService<PacoteModel>> cadastrar(@RequestBody @Valid PacoteDTO pacoteDTO) {
        var pacote = new PacoteModel();
        BeanUtils.copyProperties(pacoteDTO, pacote);



        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new MensageriaService<PacoteModel>(
                                "Pacote inserido com sucesso",
                                pacoteRepository.save(pacote),
                                HttpStatus.CREATED.value()));
    }

    @GetMapping
    public ResponseEntity<MensageriaService<List<PacoteModel>>> listar() {
        List<PacoteModel> pacotes = pacoteRepository.findAll();

        if (pacotes.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new MensageriaService<List<PacoteModel>>(
                                "Pacotes",
                                pacotes,
                                HttpStatus.OK.value()
                        )
                );

    }

    @GetMapping("/{id}")
    public ResponseEntity<MensageriaService<PacoteModel>> listarPorId(@PathVariable UUID id) {
        Optional<PacoteModel> pacote = pacoteRepository.findById(id);

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
    public ResponseEntity<MensageriaService<PacoteModel>> alterar(@RequestBody @Valid PacoteDTO pacoteDTO,
            @PathVariable UUID id) {
        Optional<PacoteModel> pacoteOpt = pacoteRepository.findById(id);

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
                                pacoteRepository.save(pacote),
                                HttpStatus.OK.value()
                        )
                );
    }

    @DeleteMapping("/{id}")
    public <T> ResponseEntity<MensageriaService<T>> deletar(@PathVariable UUID id) {
        Optional<PacoteModel> pacoteOpt = pacoteRepository.findById(id);

        if (pacoteOpt.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensageriaService<T>(
                            "Pacote não encontrado",
                            HttpStatus.NOT_FOUND.value()
                        )
                );
        }

        pacoteRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        new MensageriaService<T>(
                            "Pacote deletado com sucesso",
                            HttpStatus.ACCEPTED.value()
                        )
                ); 
    }
}
