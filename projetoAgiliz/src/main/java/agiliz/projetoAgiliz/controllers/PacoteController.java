package agiliz.projetoAgiliz.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agiliz.projetoAgiliz.models.PacoteModel;
import agiliz.projetoAgiliz.services.PacoteService;

@RestController
@RequestMapping("/pacote")
public class PacoteController {

    @Autowired
    private PacoteService service;

    @GetMapping
    public Iterable<PacoteModel> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public void cadastrar(@RequestBody PacoteModel pacote) {
        service.cadastrar(pacote);
    }

    @PutMapping
    public void alterar(@RequestBody PacoteModel pacote){
        service.cadastrar(pacote);
    }

    @DeleteMapping("/{idPacote}")
    public void deletar(@PathVariable UUID idPacote){
        service.deletar(idPacote);
    }
}
