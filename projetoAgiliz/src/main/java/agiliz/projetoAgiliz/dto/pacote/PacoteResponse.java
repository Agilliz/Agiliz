package agiliz.projetoAgiliz.dto.pacote;

import agiliz.projetoAgiliz.models.Pacote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PacoteResponse {
    private String tipo;
    private String status;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;

    public PacoteResponse(Pacote pacote) {
        this.tipo = pacote.getTipo().getAlias();
        this.status = pacote.getStatus().getAlias();
        this.dataColeta = pacote.getDataColeta();
        this.dataEntrega = pacote.getDataEntrega();
    }
}
