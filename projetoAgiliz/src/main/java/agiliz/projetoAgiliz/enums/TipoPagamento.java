package agiliz.projetoAgiliz.enums;

import java.util.Arrays;

public enum TipoPagamento {
    ZONA_NORMAL(1, "zona normal"),
    ZONA_NOVA(2, "zona nova"),
    SALARIO(3, "salário");

    private final int codigo;
    private final String alias;

    TipoPagamento(int codigo, String alias){
        this.codigo = codigo;
        this.alias = alias;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getAlias() {
        return alias;
    }

    public static TipoPagamento valueOf(Integer codigo){
        for(TipoPagamento tipo : TipoPagamento.values()){
            if(codigo == tipo.getCodigo()) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
