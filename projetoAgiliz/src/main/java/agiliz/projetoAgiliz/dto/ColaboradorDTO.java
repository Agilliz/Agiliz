package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record ColaboradorDTO(
        @NotBlank String nomeColaborador,
        @NotBlank String CPF,
        @NotBlank String RG,
        @NotBlank String classeCarteira,
        @NotNull  Date dataNascimento,
        @NotBlank String emailColaborador,
        @NotBlank String senhaColaborador,
        @NotNull Date dataAdmissao,
        @NotBlank String telefoneColaborador
) {}
