package agiliz.projetoAgiliz.models;

import agiliz.projetoAgiliz.enuns.StatusPacote;
import agiliz.projetoAgiliz.enuns.TipoPacote;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pacote")
public class PacoteModel {
    TipoPacote tipoPacote;
    StatusPacote status;
}
