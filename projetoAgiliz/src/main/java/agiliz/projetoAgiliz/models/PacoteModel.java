package agiliz.projetoAgiliz.models;

import java.util.UUID;

import agiliz.projetoAgiliz.enuns.StatusPacote;
import agiliz.projetoAgiliz.enuns.TipoPacote;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private TipoPacote tipo;

    @Enumerated(EnumType.STRING)
    private StatusPacote status;

    @ManyToOne
    private FuncionarioModel idFuncionario;
}
