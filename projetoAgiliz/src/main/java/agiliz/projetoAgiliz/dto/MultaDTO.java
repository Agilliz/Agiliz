package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.MotoristaDaVezModel;
import agiliz.projetoAgiliz.models.FuncionarioModel;
import agiliz.projetoAgiliz.models.VeiculoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MultaDTO(
        @NotEmpty FuncionarioModel fkfuncionario,
        @NotEmpty MotoristaDaVezModel fkVeiculoFuncionario,
        @NotEmpty VeiculoModel fkVeiculo,
        @NotNull Double valor,
        @NotBlank String Infracao,
        @NotNull Integer pontuacao,
        @NotBlank String gravidade,
        @NotBlank String cep,
        @NotBlank String rua,
        @NotNull  int numero
) {
}
