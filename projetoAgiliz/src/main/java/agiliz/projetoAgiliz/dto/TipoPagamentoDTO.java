package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoPagamentoDTO(
        @NotBlank
        String descricao
) {}
