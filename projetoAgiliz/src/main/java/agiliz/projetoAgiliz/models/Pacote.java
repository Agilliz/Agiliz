package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import org.springframework.cglib.core.Local;

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
    private int tipo;
    private int status;
    private boolean pagamentoFeito;
    private LocalDateTime dataColeta;
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_zona")
    private Zona zona;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_colaborador")
    private Colaborador colaborador;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_destinatario")
    private Destinatario destinatario;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_unidade")
    private Unidade unidade;

    public Pacote(int status) {
        this.status = status;
    }

    public StatusPacote getStatus() {
        return StatusPacote.valueOf(this.status);
    }

    public TipoPacote getTipo() {
        return TipoPacote.valueOf(this.tipo);
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
