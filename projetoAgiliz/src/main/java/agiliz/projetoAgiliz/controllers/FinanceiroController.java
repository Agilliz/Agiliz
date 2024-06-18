package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.DadosFinanceiros;
import agiliz.projetoAgiliz.services.FinanceiroService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/dados-financeiros")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class FinanceiroController {

    private final FinanceiroService service;
    
    @GetMapping
    public ResponseEntity<DadosFinanceiros> getDadosFinanceiros() {
        return ok().body(service.getDadosDash());
    }
}