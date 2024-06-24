package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Colaborador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnderecoFinalDTO(
            @NotNull UUID fkFuncionario,
            @NotBlank String apelido,
            @NotBlank String cep,
            @NotBlank String rua,
             @NotNull int numero
) {}
