package agiliz.projetoAgiliz.dto;

import java.time.LocalDateTime;

public record ColetasPorTempo(
        int quantidadeColetas,
        LocalDateTime dataHora
) {}
