package agiliz.projetoAgiliz.dto.pagamento;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record PagamentoRequest(
        @NotNull UUID fkTipoColaborador,
        @NotNull UUID fkFuncionario,
        @NotNull Double remuneracao,
        @NotNull int tipoPagamento
) {}
