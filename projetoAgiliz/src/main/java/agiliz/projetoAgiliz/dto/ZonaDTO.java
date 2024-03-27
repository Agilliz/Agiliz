package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ZonaDTO(
        @NotBlank String nomeZona,
        @NotNull Double valor,
        @NotNull Integer limiteSuperiorCEP,
        @NotNull Integer limiteInferiorCEP,
        @NotNull int tipoZona
) {}