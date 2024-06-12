package agiliz.projetoAgiliz.dto;

import java.util.List;

public record DadosColeta (
       int entregasRealizadas,
       int entregasAusentes,
       int entregasEmRota,
       String nomeColaboradorMenorEntrega,
       String nomeColaboradorMaiorEntrega,
       int quantidadeEntregas,
       int entregasCanceladas,
       List<ZonaRanking> zonasRankeadas
) {}
