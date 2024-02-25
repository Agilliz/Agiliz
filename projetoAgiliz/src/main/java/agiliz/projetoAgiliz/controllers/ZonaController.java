package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.repositories.IZonaRepository;

import java.util.UUID;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zona")
public class ZonaController {
    @Autowired
    private IZonaRepository repository;

    @GetMapping
    public List<ZonaModel> listar() {
        return repository.findAll();
    }

    @PostMapping
    public void cadastrar(@RequestBody ZonaModel zona) {
        repository.save(zona);
    }

    @PutMapping
    public String alterar(@RequestBody ZonaModel zona) {
        repository.save(zona);
        return "PEREÇa";
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
