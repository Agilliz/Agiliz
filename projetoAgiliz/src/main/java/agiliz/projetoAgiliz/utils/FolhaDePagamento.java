package agiliz.projetoAgiliz.utils;

import java.util.List;

public record FolhaDePagamento(List<ContratoColaborador> colaboradores) {

    public Double getTotal() {
        return colaboradores.stream()
                .mapToDouble(ContratoColaborador::getPagamento)
                .sum();
    }
}
