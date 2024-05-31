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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "unidade")
public class Unidade implements Serializable, IObservador {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUnidade;

    @JsonIgnore
    @OneToMany(mappedBy = "unidade")
    private List<Pacote> pacotes;

    private String nomeUnidade;
    private String cnpj;
    private String rua;
    private String cep;
    private Integer numero;
    private String telefoneUnidade;
    private Double retornoTotal;

    public void incrementarRetorno(double precoPacote){
        retornoTotal += precoPacote;
    }

    @Override
    public void receberAtualizacoes() {

    }
}
