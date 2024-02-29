package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.FuncionarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnderecoFinalDTO(
                               @NotEmpty FuncionarioModel fkfuncionario,
                               @NotBlank String apelido,
                               @NotBlank String cep,
                               @NotBlank String rua,
                               @NotNull int numero
) {}
