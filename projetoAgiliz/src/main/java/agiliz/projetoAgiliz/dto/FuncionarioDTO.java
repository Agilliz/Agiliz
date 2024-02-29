package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.MotoristaDaVezModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public record FuncionarioDTO(
        @NotBlank String nomeFuncionario,
        @NotBlank String CPF,
        @NotBlank String RG,
        @NotBlank String classeCarterira,
        @NotNull  Date dataNascimento,
        @NotBlank String emailFuncionario,
        @NotBlank String senhaFuncionario,
        @NotNull Date dataAdmissão,
        @NotBlank String telefoneFuncionario,
        @NotEmpty List<MotoristaDaVezModel>listFrota
) {}
