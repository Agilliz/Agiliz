package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import agiliz.projetoAgiliz.utils.IObservador;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidade")
public class Unidade implements Serializable, IObservador {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUnidade;

    @ManyToOne
    @JoinColumn(name = "fk_fornecedor")
    private Fornecedor fornecedor;

    @JsonIgnore
    @OneToMany(mappedBy = "unidade", fetch = FetchType.LAZY)
    private List<Pacote> pacotes;

    private String rua;
    private String cep;
    private Integer numero;
    private String digitosVerificadores;
    private String telefoneUnidade;

    @Override
    public void receberAtualizacoes() {

    }
}
