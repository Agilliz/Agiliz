package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record PagamentoDTO(
        @NotNull UUID fkTipoColaborador,
        @NotNull UUID fkFuncionario,
        @NotNull Double remuneracao,
        @NotNull int tipoPagamento
) {}
