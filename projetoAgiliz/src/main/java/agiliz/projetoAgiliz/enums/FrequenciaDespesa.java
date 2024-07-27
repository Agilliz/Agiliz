package agiliz.projetoAgiliz.enums;

import lombok.Getter;

@Getter
public enum FrequenciaDespesa {
    FIXA(1,"Fixa"),
    VARIAVEL(2  ,"Variável");

    private final int codigo;
    private final String alias;

    FrequenciaDespesa(int codigo, String alias) {
        this.codigo = codigo;
        this.alias = alias;
    }

    public static FrequenciaDespesa valueOf(int codigo) {
        for (FrequenciaDespesa tipo : FrequenciaDespesa.values()) {
            if (codigo == tipo.getCodigo()) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
