package agiliz.projetoAgiliz.dto.despesa;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DespesaRequest(
        @NotNull Integer tipoDespesa,
        @NotNull Double valorDespesa,
        @NotNull Integer frequenciaDespesa,
        UUID fkVeiculo
) {}
