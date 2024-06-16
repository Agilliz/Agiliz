package agiliz.projetoAgiliz.dto;

import java.time.LocalDateTime;

public record ColetasPorTempo(
        long quantidadeColetas,
        LocalDateTime dataHora
) {}
