package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.PrecificacaoZona;
import agiliz.projetoAgiliz.models.Unidade;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Date;

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
