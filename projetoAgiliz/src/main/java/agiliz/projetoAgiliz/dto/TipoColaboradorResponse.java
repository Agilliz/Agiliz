package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.TipoColaborador;
import lombok.Getter;


@Getter
public class TipoColaboradorResponse {
    private final boolean taxado;
    private final String descricao;
    private final String vigencia;

    public TipoColaboradorResponse(TipoColaborador tipo) {
        this.taxado = tipo.getTaxado();
        this.descricao = tipo.getDescricao();
        this.vigencia = tipo.getVigencia().getAlias();
    }
}
