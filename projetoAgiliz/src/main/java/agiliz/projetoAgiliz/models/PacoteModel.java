package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Enumerated(EnumType.STRING)
    private TipoPacote tipo;

    @Enumerated(EnumType.STRING)
    private StatusPacote status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_zona")
    private ZonaModel zona;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_funcionario")
    private FuncionarioModel funcionario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_destinatario")
    private DestinatarioModel destinatario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_unidade")
    private UnidadeModel unidade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idFuncionario")
    private FuncionarioModel idFuncionario;
}
