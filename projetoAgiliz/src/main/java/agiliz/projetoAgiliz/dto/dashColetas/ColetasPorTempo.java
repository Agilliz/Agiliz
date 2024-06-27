package agiliz.projetoAgiliz.dto.dashColetas;

import java.time.LocalDateTime;

public record ColetasPorTempo(
        long quantidadeColetas,
        LocalDateTime dataHora
) {}
