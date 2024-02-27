package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidade")
public class UnidadeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUnidade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_fornecedor")
    private FornecedorModel fornecedor;

    private UUID idFornecedor;

    @JsonIgnore
    @OneToMany(mappedBy = "unidade")
    private List<PacoteModel> pacotes; 

    private String rua;
    private String cep;
    private Integer numero;
    private String digitosVerificadores;
    private String telefoneUnidade;
}
