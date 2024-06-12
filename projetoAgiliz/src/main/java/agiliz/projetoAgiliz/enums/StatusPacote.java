package agiliz.projetoAgiliz.enums;


import lombok.Getter;

@Getter
public enum StatusPacote {
    COLETA(1, "coleta"),
    A_CAMINHO(2, "a caminho"),
    ENTREGUE(3, "entregue"),
    CANCELADO(4, "cancelado"),
    DEVOLVIDO(5, "devolvido"),
    AUSENTE(6, "ausente");

    private final int codigo;
    private final String alias;

    StatusPacote(int codigo, String alias) {
        this.codigo = codigo;
        this.alias = alias;
    }

    public static StatusPacote valueOf(Integer codigo){
        for(StatusPacote tipo : StatusPacote.values()){
            if(codigo == tipo.getCodigo()) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
