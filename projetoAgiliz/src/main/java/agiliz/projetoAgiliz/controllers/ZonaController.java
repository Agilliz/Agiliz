package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.services.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zona")
public class ZonaController {

    @Autowired
    private ZonaService service;

    @GetMapping
    public Iterable<ZonaModel> listar(){
        return service.listarZonas();
    }
}
