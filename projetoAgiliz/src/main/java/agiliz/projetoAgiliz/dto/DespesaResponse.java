package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.enums.TipoDespesa;
import agiliz.projetoAgiliz.models.Despesa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DespesaResponse {
    private final String tipoDespesa;
    private final String frequenciaDespesa;
    private final double valorDespesa;
    private final VeiculoResponse veiculo;

    public DespesaResponse(Despesa despesa) {
        this.tipoDespesa = despesa.getTipoDespesa().getAlias();
        this.frequenciaDespesa = despesa.getFrequenciaDespesa().getAlias();
        this.valorDespesa = despesa.getValorDespesa();
        if(despesa.getTipoDespesa() == TipoDespesa.IPVA) this.veiculo = new VeiculoResponse(despesa.getVeiculo());
        else this.veiculo = null;
    }
}
