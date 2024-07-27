package agiliz.projetoAgiliz.dto.enderecoFinal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnderecoFinalRequest(
            @NotNull UUID fkFuncionario,
            @NotBlank String apelido,
            @NotBlank String cep,
            @NotBlank String rua,
             @NotNull int numero
) {}
