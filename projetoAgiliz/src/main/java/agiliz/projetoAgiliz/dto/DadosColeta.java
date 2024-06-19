package agiliz.projetoAgiliz.dto;

import java.time.LocalTime;
import java.util.List;

public record DadosColeta (
       long pacotesColetados,
       long pacotesAusentes,
       long pacotesEmRota,
       long pacotesAguardandoColeta,
       long coletasRealizadas,
       long coletasCanceladas,
       String nomeClienteMenorColeta,
       String nomeClienteMaiorColeta,
       LocalTime horaCorteMedia,
       List<ZonaRanking> zonasRankeadas,
       List<ColetasPorTempo> coletasPorTempo
) {}
