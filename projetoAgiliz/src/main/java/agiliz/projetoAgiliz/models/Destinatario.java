package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import agiliz.projetoAgiliz.utils.IObservador;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "destinatario")
public class Destinatario implements Serializable, IObservador {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idDestinatario;

    private String nomeDestinatario;
    private String ruaDestinatario;
    private String cepDestinatario;
    private Integer numeroDestinatario;

    @JsonIgnore
    @OneToMany(mappedBy = "destinatario")
    private List<Pacote> pacotes;

    @Override
    public void receberAtualizacoes() {

    }
}
