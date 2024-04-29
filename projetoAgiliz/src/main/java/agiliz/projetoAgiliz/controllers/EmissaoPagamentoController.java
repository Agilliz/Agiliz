package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.models.EmissaoPagamento;
import agiliz.projetoAgiliz.services.EmissaoPagamentoService;
import agiliz.projetoAgiliz.utils.CalculadoraDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emissao-pagamento")
@CrossOrigin
public class EmissaoPagamentoController {
    @Autowired
    private EmissaoPagamentoService service;

    @GetMapping
    public List<EmissaoPagamento> getEmissoes(){
        return service.listar();
    }

    @GetMapping("/teste")
    public LocalDate teste(){
        Date data = CalculadoraDatas.calcularQuintoDiaUtil(LocalDate.now());
        return LocalDate.of(data.getYear() - 1, data.getMonth() - 1, data.getDay() - 1);
    }
}
