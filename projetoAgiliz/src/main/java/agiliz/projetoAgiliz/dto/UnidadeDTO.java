package agiliz.projetoAgiliz.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UnidadeDTO(
        UUID idFornecedor,
        @NotBlank String rua,
        @NotBlank String cep,
        @PositiveOrZero int numero,
        @NotBlank String digitosVerificadores,
        @NotBlank String telefoneUnidade)
{}
