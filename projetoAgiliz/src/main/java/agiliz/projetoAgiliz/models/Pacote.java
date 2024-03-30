package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import agiliz.projetoAgiliz.enums.StatusPacote;
import agiliz.projetoAgiliz.enums.TipoPacote;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pacote")
public class Pacote implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPacote;

    private TipoPacote tipo;

    private StatusPacote status;

    private boolean pagamentoFeito;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_zona")
    private Zona zona;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_colaborador")
    private Colaborador colaborador;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_destinatario")
    private Destinatario destinatario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_unidade")
    private Unidade unidade;

    public Pacote(int tipoPacote, int statusPacote){
        setStatusPacote(statusPacote);
        setTipoPacote(tipoPacote);
    }

    public void setStatusPacote(int statusPacote){
        this.status = StatusPacote.valueOf(statusPacote);
    }

    public void setTipoPacote(int tipoPacote){
        this.tipo = TipoPacote.valueOf(tipoPacote);
    }

    @Override
    public String toString() {
        return "Pacote{" +
                "idPacote=" + idPacote +
                ", tipo=" + tipo +
                ", status=" + status +
                ", zona=" + zona +
                ", colaborador=" + colaborador +
                ", destinatario=" + destinatario +
                ", unidade=" + unidade +
                '}';
    }
}
