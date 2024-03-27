package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TipoColaboradorDTO (
        @NotNull String descricao,
        boolean taxado,
        @NotNull UUID fkVigencia
){}
