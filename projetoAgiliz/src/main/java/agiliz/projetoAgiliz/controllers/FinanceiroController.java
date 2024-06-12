package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.DadosFinanceiros;
import agiliz.projetoAgiliz.services.FinanceiroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dados-financeiros")
@RestController
@CrossOrigin
public class FinanceiroController {

    @Autowired
    private FinanceiroService service;
    
    @GetMapping("/dashboard-completa")
    public DadosFinanceiros getDadosFinanceiros() {
        return service.getDadosDash();
    }

}
