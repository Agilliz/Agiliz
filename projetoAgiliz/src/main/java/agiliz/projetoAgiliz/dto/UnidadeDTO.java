package agiliz.projetoAgiliz.dto;


import agiliz.projetoAgiliz.models.Fornecedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UnidadeDTO(
        Fornecedor fornecedor,
        @NotBlank String rua,
        @NotBlank String cep,
        @PositiveOrZero int numero,
        @NotBlank String digitosVerificadores,
        @NotBlank String telefoneUnidade)
{}
