package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EmissaoPagamentoDTO(
        @NotNull UUID fkPagamento
) {}
