package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.services.ZonaService;

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

@RestController
@RequestMapping("/zona")
public class ZonaController {

    @Autowired
    private ZonaService service;

    @GetMapping
    public Iterable<ZonaModel> listar(){
        return service.listarTodos();
    }

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody ZonaModel zona){
        service.cadastrar(zona);
    }

    @PutMapping("/alterar")
    public void alterar(@RequestBody ZonaModel zona){
        service.cadastrar(zona);
    }

    @DeleteMapping("/deletar/{idZona}")
    public void deletar(@PathVariable UUID idZona){
        service.deletarPorId(idZona);
    }
}
