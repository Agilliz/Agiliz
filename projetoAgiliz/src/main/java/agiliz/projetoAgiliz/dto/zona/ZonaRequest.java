package agiliz.projetoAgiliz.dto.zona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ZonaRequest(
        @NotBlank String nomeZona,
        @NotNull Double valor,
        @NotNull Integer limiteSuperiorCEP,
        @NotNull Integer limiteInferiorCEP,
        @NotNull int tipoZona
) {}