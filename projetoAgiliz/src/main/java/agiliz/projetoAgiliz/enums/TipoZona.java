package agiliz.projetoAgiliz.enums;

public enum TipoZona {
    ZONA_NORMAL(1, "zona normal"),
    ZONA_NOVA(2, "zona nova");
    private final int codigo;
    private final String alias;

    TipoZona(int codigo, String alias){
        this.codigo = codigo;
        this.alias = alias;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getAlias() {
        return alias;
    }

    public static TipoZona valueOf(int codigo){
        for(TipoZona tipo : TipoZona.values()){
            if(codigo == tipo.getCodigo()) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
