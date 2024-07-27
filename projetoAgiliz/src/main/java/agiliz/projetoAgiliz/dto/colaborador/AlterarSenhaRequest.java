package agiliz.projetoAgiliz.dto.colaborador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AlterarSenhaRequest(
    @NotNull UUID idColaborador,
    @NotBlank String senha
) {}
