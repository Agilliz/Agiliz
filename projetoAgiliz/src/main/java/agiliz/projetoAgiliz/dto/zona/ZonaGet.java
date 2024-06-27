package agiliz.projetoAgiliz.dto.zona;

import agiliz.projetoAgiliz.models.Zona;
import lombok.Getter;


@Getter
public class ZonaGet {
    private String nomeZona;
    private Double valor;
    private Integer limiteSuperiorCEP;
    private Integer limiteInferiorCEP;
    private String tipoZona;

    public ZonaGet(Zona zona){
        this.nomeZona = zona.getNomeZona();
        this.valor = zona.getValor();
        this.limiteSuperiorCEP = zona.getLimiteSuperiorCEP();
        this.limiteInferiorCEP = zona.getLimiteInferiorCEP();
        this.tipoZona = zona.getTipoZona().getAlias();
    }
}
