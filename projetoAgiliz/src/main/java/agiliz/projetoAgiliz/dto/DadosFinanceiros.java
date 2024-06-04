package agiliz.projetoAgiliz.dto;
public record DadosFinanceiros(
    Double lucroBruto,
    Double lucroLiquido,
    String clienteMaiorRetorno,
    String clienteMenorRetorno,
    Double ticketMedio,
    Double totalDespesasFixas,
    Double totalDespesasVariaveis,
    Double totalImposto,
    Double taxas,
    Double custoOperacional
) {}
