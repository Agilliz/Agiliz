package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotNull;

public record DespesaDTO(
        @NotNull Integer tipoDespesa,
        @NotNull Double valorDespesa
) {}
