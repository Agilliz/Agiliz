package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.util.UUID;

import agiliz.projetoAgiliz.enums.StatusPacote;
import agiliz.projetoAgiliz.enums.TipoPacote;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pacote")
public class PacoteModel implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPacote;

    @ManyToOne
    @JoinColumn(name = "fk_zona")
    private ZonaModel zona; 

    @Enumerated(EnumType.STRING)
    private TipoPacote tipo;

    @Enumerated(EnumType.STRING)
    private StatusPacote status;

    private UUID idDestinatario;
}
