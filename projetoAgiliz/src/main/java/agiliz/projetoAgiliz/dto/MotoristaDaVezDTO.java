package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.FuncionarioModel;
import agiliz.projetoAgiliz.models.VeiculoModel;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record MotoristaDaVezDTO(
        @NotEmpty FuncionarioModel funcionario,
        @NotEmpty VeiculoModel veiculo
) {}
