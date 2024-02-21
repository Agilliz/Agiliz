package agiliz.projetoAgiliz.controllers;

import lombok.Getter;


import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agiliz.projetoAgiliz.models.Endereco;
import agiliz.projetoAgiliz.services.RotaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/rota")
public class RotaController {

    @PostMapping("/calcular-rota")
    public List<String> calcularRota(@RequestBody RotaDTO rota){
        return RotaService.calcularRota(rota.getEntregas(), rota.getInicio(), rota.getFim());
    }

    @PostMapping("/calcular-distancia")
    public double calcularDinstancia(@RequestBody DistanciaDTO distanciaDTO){
        return RotaService.calcularDistancia(distanciaDTO.getEndereco1(), distanciaDTO.getEndereco2());
    }
    
}

@Getter
class RotaDTO{
    List<Endereco> entregas;
    Endereco inicio;
    Endereco fim;
}

@Getter
class DistanciaDTO{
    Endereco endereco1;
    Endereco endereco2;
}
