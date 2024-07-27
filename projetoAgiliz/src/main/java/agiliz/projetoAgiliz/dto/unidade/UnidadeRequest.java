package agiliz.projetoAgiliz.dto.unidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalTime;

public record UnidadeRequest(
        @NotBlank String rua,
        @NotBlank String cep,
        @PositiveOrZero int numero,
        @NotBlank String digitosVerificadores,
        @NotBlank String telefoneUnidade,
        @NotBlank String nomeUnidade,
        @NotBlank String cnpj,
        LocalTime horarioCorte
)
{}
