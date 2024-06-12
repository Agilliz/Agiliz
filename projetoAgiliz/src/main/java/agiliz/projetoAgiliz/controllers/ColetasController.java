package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.DadosColeta;
import agiliz.projetoAgiliz.services.ColetasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/dados-coleta")
public class ColetasController {

    private final ColetasService service;

    @GetMapping
    public ResponseEntity<DadosColeta> getDadosColeta() {
        return ok().body(service.getDadosColeta());
    }
}
