package agiliz.projetoAgiliz.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import agiliz.projetoAgiliz.dto.destinatario.DestinatarioRequest;
import agiliz.projetoAgiliz.models.Destinatario;
import agiliz.projetoAgiliz.repositories.IDestinatarioRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
@SuppressWarnings("rawtypes")


@RestController
@RequestMapping("/destinatario")
@CrossOrigin
public class DestinatarioController {
    @Autowired
    IDestinatarioRepository destinatarioRepository;

    @GetMapping("/")
    public ResponseEntity<MensageriaService<Page<Destinatario>>> listDestinatarios(Pageable pageable) {
        Page listDestinatarios = destinatarioRepository.findAll(pageable);

        if (!listDestinatarios.isEmpty()) {
            MensageriaService mensageriaService = new MensageriaService<>("Destinatarios", listDestinatarios, 200);

            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idDestinatario}")
    public ResponseEntity<MensageriaService<Destinatario>> listDestinatarioById(
            @PathVariable UUID idDestinatario) {
        Optional<Destinatario> destinatario = destinatarioRepository.findById(idDestinatario);

        if (destinatario.isPresent()) {
            MensageriaService mensageriaService = new MensageriaService<>("Destinatario", destinatario, 200);

            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        MensageriaService mensageriaService = new MensageriaService<>("Destonatário não encontrado", 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MensageriaService<Destinatario>> cadastrarDestinatario(
            @RequestBody @Valid DestinatarioRequest destinatarioRequest) {
        Destinatario destinatario = new Destinatario();

        BeanUtils.copyProperties(destinatarioRequest, destinatario);

        try {
            destinatarioRepository.save(destinatario);

            MensageriaService mensageriaService = new MensageriaService<>("Destinatario cadastrado com sucesso",
                    destinatario, 201);

            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService<>("Ocorreu um erro ao cadastrar o destinatario",
                    e, 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
        }
    }

    @PutMapping("/alterar/{idDestinatario}")
    public ResponseEntity<MensageriaService<Destinatario>> alterarDestinatario(@PathVariable UUID idDestinatario, @RequestBody @Valid DestinatarioRequest destinatarioDTO){
        Optional<Destinatario> destinatario = destinatarioRepository.findById(idDestinatario);

        if (destinatario.isPresent()) {
            Destinatario destinatarioModel = new Destinatario();
            BeanUtils.copyProperties(destinatarioDTO, destinatarioModel);

            try {
                destinatarioRepository.save(destinatarioModel);
                MensageriaService mensageriaService = new MensageriaService<>("Destinatario atualizado com sucesso", destinatarioModel, 200);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
            } catch (Exception e) {
                MensageriaService mensageriaService = new MensageriaService<>("Ocorreu um erro ao atualizar o destinatario", e, 400);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
            }
        }

        MensageriaService mensageriaService = new MensageriaService<>("Destinatario não encontrado", destinatarioDTO, 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/deletar/{idDestinatario}")
    public ResponseEntity<MensageriaService<Destinatario>> deletarDestinatario(@PathVariable UUID idDestinatario){
        Optional<Destinatario> destinatario = destinatarioRepository.findById(idDestinatario);

        if(destinatario.isPresent()){
            try {
                destinatarioRepository.delete(destinatario.get());
                MensageriaService mensageriaService = new MensageriaService<>("Destinatario deletado com sucesso", destinatario, 200);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
            } catch (Exception e) {
                MensageriaService mensageriaService = new MensageriaService<>("Ocorreu um erro ao deletar o destinatário", destinatario, 400);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
            }
        }

        MensageriaService mensageriaService = new MensageriaService<>("Destinatario não encontrado", destinatario, 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }
}
