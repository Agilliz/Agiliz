package agiliz.projetoAgiliz.dto.motoristaDaVez;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Veiculo;
import jakarta.validation.constraints.NotEmpty;

public record MotoristaDaVezDTO(
        @NotEmpty Colaborador funcionario,
        @NotEmpty Veiculo veiculo
) {}
