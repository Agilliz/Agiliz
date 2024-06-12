package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.enums.StatusPacote;

import java.time.LocalDateTime;

public record PacotesDashColetas(
        StatusPacote status,
        LocalDateTime dataColeta
) {}
