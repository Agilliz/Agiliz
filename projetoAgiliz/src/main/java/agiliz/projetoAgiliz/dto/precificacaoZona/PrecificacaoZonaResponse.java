package agiliz.projetoAgiliz.dto.precificacaoZona;

import agiliz.projetoAgiliz.dto.unidade.UnidadeResponse;
import agiliz.projetoAgiliz.models.PrecificacaoZona;
import lombok.Getter;

@Getter
public class PrecificacaoZonaResponse {

    private final UnidadeResponse unidade;
    private final Double preco;
    private final int tipoZona;
    public PrecificacaoZonaResponse(PrecificacaoZona precificacaoZona) {
        this.unidade = new UnidadeResponse(precificacaoZona.getUnidade());
        this.preco = precificacaoZona.getPreco();
        this.tipoZona = precificacaoZona.getTipoZona();
    }
}
