package agiliz.projetoAgiliz.enums;

import lombok.Getter;

@Getter
public enum TipoDespesa {

    VARIAVEL(1,"Variável"),
    FIXA(1  ,"Fixa");

    private final int codigo;
    private final String alias;

    TipoDespesa(int codigo, String alias) {
        this.codigo = codigo;
        this.alias = alias;
    }

    public static TipoDespesa valueOf(Integer codigo){
        for (TipoDespesa tipo : TipoDespesa.values()){
            if (codigo == tipo.getCodigo()) return tipo;
        }
        throw new IllegalArgumentException("Código inválido");
    }
}
