package agiliz.projetoAgiliz.enums;

import lombok.Getter;

@Getter
public enum StatusPacote {
    COLETA("coleta"),
    A_CAMINHO("a caminho"),
    ENTREGUE("entregue");

    private final String alias;

    StatusPacote(String alias) {
        this.alias = alias;
    }
}
