package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Despesa;
import lombok.Getter;

@Getter
public class DespesaResponse {
    private final String tipoDespesa;
    private final String frequenciaDespesa;
    private final double valorDespesa;

    public DespesaResponse(Despesa despesa) {
        this.tipoDespesa = despesa.getTipoDespesa().getAlias();
        this.frequenciaDespesa = despesa.getFrequenciaDespesa().getAlias();
        this.valorDespesa = despesa.getValorDespesa();
    }
}
