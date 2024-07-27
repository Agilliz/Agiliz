package agiliz.projetoAgiliz.dto.tipoColaborador;

import jakarta.validation.constraints.NotNull;

public record TipoColaboradorRequest(
        @NotNull String descricao,
        boolean taxado,
        @NotNull int vigencia
){}
