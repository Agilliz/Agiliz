package agiliz.projetoAgiliz.enums;

import lombok.Getter;

@Getter
public enum Vigencia {
    MENSAL(1, "mensal"),
    QUINZENAL(2, "quinzenal"),
    SEMANAL(3, "semanal");

    private final int codigo;
    private final String alias;

    Vigencia(int codigo, String alias){
        this.codigo = codigo;
        this.alias = alias;
    }

    public static Vigencia valueOf(int codigo){
        for(Vigencia tipo : Vigencia.values()){
            if(tipo.getCodigo() == codigo) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }

    
}
