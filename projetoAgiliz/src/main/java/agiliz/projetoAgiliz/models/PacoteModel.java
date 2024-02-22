package agiliz.projetoAgiliz.models;

import java.util.UUID;

import agiliz.projetoAgiliz.enuns.StatusPacote;
import agiliz.projetoAgiliz.enuns.TipoPacote;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pacote")
public class PacoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPacote;

    TipoPacote tipoPacote;
    StatusPacote status;
}
