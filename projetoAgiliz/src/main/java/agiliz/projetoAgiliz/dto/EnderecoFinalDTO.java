package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Colaborador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EnderecoFinalDTO(
                               @NotEmpty Colaborador fkfuncionario,
                               @NotBlank String apelido,
                               @NotBlank String cep,
                               @NotBlank String rua,
                               @NotNull int numero
) {}
