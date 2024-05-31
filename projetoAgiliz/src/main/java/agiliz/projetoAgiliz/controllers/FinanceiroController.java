package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.DadosFinanceiros;
import agiliz.projetoAgiliz.services.FinanceiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dados-financeiros")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class FinanceiroController {

    private final FinanceiroService service;

    @GetMapping("/dashboard-completa")
    public DadosFinanceiros getDadosFinanceiros(){
        return service.getDadosDash();
    }


}
