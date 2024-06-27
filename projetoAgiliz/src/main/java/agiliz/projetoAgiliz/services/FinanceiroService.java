package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.dashFinanceira.DadosFinanceiros;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceiroService {
    private final PagamentoService pagamentoService;
    private final IPacoteRepository pacoteRepository;
    private final UnidadeService unidadeService;

    public DadosFinanceiros getDadosDash(){
        var tickets = pacoteRepository.findZonaPrices();

        var lucroBruto = tickets.stream()
                .mapToDouble(v -> v)
                .sum();

        var custoOperacional = pagamentoService.gerarFolhaDePagamento().getTotal();
        var ticketMedio = lucroBruto / tickets.size();
        var lucroLiquido = lucroBruto - custoOperacional;

        return new DadosFinanceiros(
                lucroBruto,
                lucroLiquido,
                unidadeService.getNomeUnidadeMaiorRetorno(),
                unidadeService.getNomeUnidadeMenorRetorno(),
                ticketMedio,
                0.,
                0.,
                0.,
                0.,
                custoOperacional
        );
    }
}
