package agiliz.projetoAgiliz.dto.destinatario;

import agiliz.projetoAgiliz.models.Destinatario;
import lombok.Getter;

@Getter
public class DestinatarioResponse {
    private final String nomeDestinatario;
    private final String ruaDestinatario;
    private final String cepDestinatario;
    private final Integer numeroDestinatario;

    public DestinatarioResponse(Destinatario destinatario) {
        this.nomeDestinatario = destinatario.getNomeDestinatario();
        this.ruaDestinatario = destinatario.getRuaDestinatario();
        this.cepDestinatario = destinatario.getCepDestinatario();
        this.numeroDestinatario = destinatario.getNumeroDestinatario();
    }
}
