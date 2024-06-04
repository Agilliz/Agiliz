package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.DadosFinanceiros;
import agiliz.projetoAgiliz.dto.UnidadeSimples;
import agiliz.projetoAgiliz.models.Pacote;
import agiliz.projetoAgiliz.models.Unidade;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;
import agiliz.projetoAgiliz.repositories.IUnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceiroService {

    private final PagamentoService pagamentoService;

    private final IPacoteRepository pacoteRepository;

    private final IUnidadeRepository unidadeRepository;

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
                unidadeRepository.findUnidadeMaiorRetorno(),
                unidadeRepository.findUnidadeMenorRetorno(),
                ticketMedio,
                0.,
                0.,
                0.,
                0.,
                custoOperacional
        );
    }
}
