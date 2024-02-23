package agiliz.projetoAgiliz.controllers;

import java.util.List;
import java.util.UUID;

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

import agiliz.projetoAgiliz.models.FornecedorModel;
import agiliz.projetoAgiliz.models.PacoteModel;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.services.PacoteService;

@RestController
@RequestMapping("/pacote")
public class PacoteController {

    @Autowired
    private PacoteService service;

    @GetMapping
    public ResponseEntity<MensageriaService<List<PacoteModel>>> listar() {
        List<PacoteModel> pacotes = service.listarTodos();

        if (pacotes.isEmpty()){
            return ResponseEntity.status(204)
                .body(
                    new MensageriaService<>(
                        "Nenhum pacote encontrado",
                        "No content",
                        204)
                );
        }

        return ResponseEntity.status(200)
            .body(
                new MensageriaService<List<PacoteModel>>(
                    "Pacotes",
                    pacotes,
                    200
                )
            );

    }

    @PostMapping
    public void cadastrar(@RequestBody PacoteModel pacote) {
        service.cadastrar(pacote);
    }

    @PutMapping
    public void alterar(@RequestBody PacoteModel pacote) {
        service.cadastrar(pacote);
    }

    @DeleteMapping("/{idPacote}")
    public void deletar(@PathVariable UUID idPacote) {
        service.deletar(idPacote);
    }
}
