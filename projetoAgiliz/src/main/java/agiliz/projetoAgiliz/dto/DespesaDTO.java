package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DespesaDTO(
        @NotNull Integer tipoDespesa,
        @NotNull Double valorDespesa,
        @NotNull Integer frequenciaDespesa,
        UUID fkVeiculo
) {}
