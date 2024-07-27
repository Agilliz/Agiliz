package agiliz.projetoAgiliz.dto.comprovanteEntrega;

import agiliz.projetoAgiliz.dto.destinatario.DestinatarioResponse;
import agiliz.projetoAgiliz.dto.pacote.PacoteResponse;
import agiliz.projetoAgiliz.dto.unidade.UnidadeResponse;
import agiliz.projetoAgiliz.dto.zona.ZonaResponse;
import agiliz.projetoAgiliz.models.ComprovanteEntrega;
import agiliz.projetoAgiliz.repositories.IComprovanteEntregaRepository;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ComprovanteEntregaResponse {
    private final UnidadeResponse unidade;
    private final DestinatarioResponse destinatario;
    private final PacoteResponse pacote;
    private final ZonaResponse zona;
    private final LocalDateTime dataHoraEmissao;

    public ComprovanteEntregaResponse(ComprovanteEntrega comprovante) {
        this.unidade = new UnidadeResponse(comprovante.getPacote().getUnidade());
        this.destinatario = new DestinatarioResponse(comprovante.getPacote().getDestinatario());
        this.pacote = new PacoteResponse(comprovante.getPacote());
        this.zona = new ZonaResponse(comprovante.getPacote().getZona());
        this.dataHoraEmissao = comprovante.getDataHoraEmissao();
    }
}
