package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Veiculo;
import lombok.Getter;

@Getter
public class VeiculoResponse {
    private final String tipoVeiculo;
    private final String proprietario;
    private final String placa;

    public VeiculoResponse(Veiculo veiculo) {
        this.tipoVeiculo = veiculo.getTipoVeiculo();
        this.proprietario = veiculo.getProprietario();
        this.placa = veiculo.getPlaca();
    }
}
