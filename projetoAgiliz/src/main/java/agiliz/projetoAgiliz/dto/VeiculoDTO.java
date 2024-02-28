package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record VeiculoDTO(
         @NotBlank String tipoVeiculo,
         @NotBlank String proprietario,
         @NotBlank String placa
) {}
