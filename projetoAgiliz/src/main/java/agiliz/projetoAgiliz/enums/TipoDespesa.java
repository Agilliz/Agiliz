package agiliz.projetoAgiliz.enums;

import lombok.Getter;

@Getter
public enum TipoDespesa {

    ALUGUEL(1,"Aluguel"),
    LUZ(2  ,"Luz"),
    AGUA(3, "Água"),
    CUSTO_OPERACIONAL(4, "Custo operacional"),
    IPVA(5, "IPVA");

    private final int codigo;
    private final String alias;

    TipoDespesa(int codigo, String alias) {
        this.codigo = codigo;
        this.alias = alias;
    }

    public static TipoDespesa valueOf(int codigo) {
        for (TipoDespesa tipo : TipoDespesa.values()) {
            if (codigo == tipo.getCodigo()) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
