package agiliz.projetoAgiliz.enums;

import lombok.Getter;

@Getter
public enum TipoPacote {
    ENTREGA(1, "entrega"),
    COLETA(2, "coleta");

    private final int codigo;
    private final String alias;

    TipoPacote(int codigo, String alias) {
        this.codigo = codigo;
        this.alias = alias;
    }

    public static TipoPacote valueOf(Integer codigo){
        for(TipoPacote tipo : TipoPacote.values()){
            if(codigo == tipo.getCodigo()) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }

    public int getCodigo() {
        return codigo;
    }

    public String getAlias() {
        return alias;
    }
}
