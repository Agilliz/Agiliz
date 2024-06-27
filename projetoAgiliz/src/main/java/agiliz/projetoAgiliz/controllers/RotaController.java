package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.rota.Rota;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import agiliz.projetoAgiliz.services.RotaService;

@RestController
@RequestMapping("/rota")
@CrossOrigin
public class RotaController {

    @Autowired
    private RotaService service;

    @GetMapping("/calcular-rota")
    public List<String> calcularRota(@RequestParam String rotaString) throws JsonMappingException, JsonProcessingException {
        
        Rota rota = new ObjectMapper().readValue(rotaString, Rota.class);
        return service.calcularRota(rota.getEntregas(), rota.getInicio(), rota.getFim());
    }
}
