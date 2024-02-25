package agiliz.projetoAgiliz.services;

import java.util.*;

import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.models.CalculadoraRotas;
import agiliz.projetoAgiliz.models.Endereco;

@Service
public class RotaService {
    public List<String> calcularRota(
            List<Endereco> entregas,
            Endereco inicio,
            Endereco fim
    ) {
        List<Endereco> enderecos = new ArrayList<>(entregas);
        enderecos.addAll(List.of(inicio, fim));
        CalculadoraRotas calculadoraRotas = new CalculadoraRotas(enderecos);
        List<String> rota = calculadoraRotas.gerarRota(inicio.getId(), fim.getId());
        Collections.reverse(rota);
        return rota;
    }

    public Double calcularDistancia(Endereco endereco1, Endereco endereco2){
        return CalculadoraRotas.calcularHarvesine(endereco1, endereco2);
    }
}