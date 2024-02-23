package agiliz.projetoAgiliz.dto;

import java.util.UUID;

import agiliz.projetoAgiliz.models.FornecedorModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UnidadeDTO(FornecedorModel fornecedor, @NotBlank String rua, @NotBlank String cep, @PositiveOrZero int numero,
                        @NotBlank String digitosVerificadores, @NotBlank String telefoneUnidade) {

}
