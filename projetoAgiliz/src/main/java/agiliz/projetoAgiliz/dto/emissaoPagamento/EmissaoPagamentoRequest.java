package agiliz.projetoAgiliz.dto.emissaoPagamento;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EmissaoPagamentoRequest(
        @NotNull UUID fkPagamento
) {}
