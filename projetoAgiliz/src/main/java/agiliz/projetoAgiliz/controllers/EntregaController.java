package agiliz.projetoAgiliz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import agiliz.projetoAgiliz.services.ColaboradorService;
import agiliz.projetoAgiliz.services.EntregaService;
import org.springframework.web.bind.annotation.GetMapping;

import agiliz.projetoAgiliz.dto.DashColetasDTO;
import agiliz.projetoAgiliz.dto.PacotePorcentagemDTO;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@CrossOrigin
@RequestMapping("/dash-entregas")
public class EntregaController {
    
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public ResponseEntity<DashColetasDTO> getMethodName() {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.montarDash());
    }
    
}
