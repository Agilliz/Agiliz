package agiliz.projetoAgiliz.enums;

import lombok.Getter;

@Getter
public enum TipoPacote {
    ENTREGA("entrega"),
    COLETA("coleta");

    private final String alias;

    TipoPacote(String alias){
        this.alias = alias;
    }
}
