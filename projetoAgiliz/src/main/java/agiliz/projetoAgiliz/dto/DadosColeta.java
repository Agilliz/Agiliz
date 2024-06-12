package agiliz.projetoAgiliz.dto;

import java.util.List;

public record DadosColeta (
       int entregasRealizadas,
       int entregasAusentes,
       int entregasEmRota,
       int entregasCanceladas,
       String nomeClienteMenorColeta,
       String nomeClienteMaiorColeta,
       List<ZonaRanking> zonasRankeadas,
       List<ColetasPorTempo> coletasPorTempo
) {}
