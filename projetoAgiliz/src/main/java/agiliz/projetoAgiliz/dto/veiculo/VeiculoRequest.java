package agiliz.projetoAgiliz.dto.veiculo;

import jakarta.validation.constraints.NotBlank;

public record VeiculoRequest(
         @NotBlank String tipoVeiculo,
         @NotBlank String proprietario,
         @NotBlank String placa
) {}
