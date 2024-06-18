package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

import org.hibernate.validator.constraints.br.CPF;

public record ColaboradorDTO(
        @NotBlank String nomeColaborador,
        @NotBlank @CPF String cpf,
        @NotBlank String rg,
        @NotBlank String classeCarteira,
        @NotNull  Date dataNascimento,
        @NotBlank String emailColaborador,
        @NotBlank String senhaColaborador,
        @NotNull Date dataAdmissao,
        @NotBlank String telefoneColaborador
) {}
