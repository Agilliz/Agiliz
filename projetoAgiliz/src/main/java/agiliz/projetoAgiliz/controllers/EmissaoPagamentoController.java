package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.models.EmissaoPagamento;
import agiliz.projetoAgiliz.services.EmissaoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emissao-pagamento")
public class EmissaoPagamentoController {
    @Autowired
    private EmissaoPagamentoService service;

    @GetMapping
    public List<EmissaoPagamento> getEmissoes(){
        return service.listar();
    }
}
