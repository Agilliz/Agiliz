package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VigenciaDTO(
    @NotNull
    @Positive
    Integer dias,

    @NotBlank
    String descricao
) {}