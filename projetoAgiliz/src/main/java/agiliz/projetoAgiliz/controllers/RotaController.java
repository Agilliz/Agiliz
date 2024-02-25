package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.DistanciaDTO;
import agiliz.projetoAgiliz.dto.RotaDTO;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import agiliz.projetoAgiliz.services.RotaService;

@RestController
@RequestMapping("/rota")
public class RotaController {

    @GetMapping("/calcular-rota")
    public List<String> calcularRota(@RequestParam String rotaString) throws JsonMappingException, JsonProcessingException {
        
        RotaDTO rota = new ObjectMapper().readValue(rotaString, RotaDTO.class);
        return RotaService.calcularRota(rota.getEntregas(), rota.getInicio(), rota.getFim());
    }

    @GetMapping("/calcular-distancia")
    public double calcularDinstancia(@RequestParam DistanciaDTO distanciaDTO) {
        return RotaService.calcularDistancia(distanciaDTO.getEndereco1(), distanciaDTO.getEndereco2());
    }
}
