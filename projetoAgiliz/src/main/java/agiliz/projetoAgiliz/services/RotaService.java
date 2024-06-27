package agiliz.projetoAgiliz.services;

import java.util.*;

import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.models.CalculadoraRotas;
import agiliz.projetoAgiliz.dto.rota.Endereco;

@Service
public class RotaService {

    public List<String> calcularRota(
            List<Endereco> entregas,
            Endereco inicio,
            Endereco fim
    ) {
        List<Endereco> enderecos = new ArrayList<>(List.of(inicio, fim));
        enderecos.addAll(entregas);
        CalculadoraRotas calculadoraRotas = new CalculadoraRotas(enderecos);
        return calculadoraRotas.gerarRota(inicio.getId(), fim.getId());
    }

    public Double calcularDistancia(Endereco endereco1, Endereco endereco2){
        return CalculadoraRotas.calcularHarvesine(endereco1, endereco2);
    }
}