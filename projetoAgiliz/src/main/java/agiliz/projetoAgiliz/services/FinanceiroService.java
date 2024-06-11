package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.DadosFinanceiros;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;
import agiliz.projetoAgiliz.repositories.IUnidadeRepository;
import agiliz.projetoAgiliz.services.PagamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FinanceiroService {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private IPacoteRepository pacoteRepository;

    @Autowired
    private IUnidadeRepository unidadeRepository;

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
