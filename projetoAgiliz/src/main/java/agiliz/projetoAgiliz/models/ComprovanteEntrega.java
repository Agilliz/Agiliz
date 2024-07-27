package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ComprovanteEntrega {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID idComprovante;

    @ManyToOne
    @JoinColumn(name = "fk_pacote")
    private Pacote pacote;

    private LocalDateTime dataHoraEmissao;

    public ComprovanteEntrega() {
        this.dataHoraEmissao = LocalDateTime.now();
    }

    // anexo discutir depois
}
