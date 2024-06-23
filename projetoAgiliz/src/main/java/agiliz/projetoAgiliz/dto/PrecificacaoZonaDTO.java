package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Unidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Date;

public record PrecificacaoZonaDTO(
        @NotEmpty Unidade fkUnidadae,
        @NotNull Double preco,
        @NotNull int tipoZona

) {}
