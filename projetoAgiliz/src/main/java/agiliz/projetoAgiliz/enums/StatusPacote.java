package agiliz.projetoAgiliz.enums;


import lombok.Getter;

@Getter
public enum StatusPacote {
    A_CAMINHO(1, "a caminho"),
    ENTREGUE(2, "entregue"),
    CANCELADO(3, "cancelado"),
    DEVOLVIDO(4, "devolvido"),
    AUSENTE(5, "ausente"),
    EM_ESPERA(6, "em espera");

    private final int codigo;
    private final String alias;

    StatusPacote(int codigo, String alias) {
        this.codigo = codigo;
        this.alias = alias;
    }

    public static StatusPacote valueOf(int codigo) {
        for(StatusPacote tipo : StatusPacote.values()) {
            if(codigo == tipo.getCodigo()) return tipo;
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
