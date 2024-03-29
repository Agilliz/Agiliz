package agiliz.projetoAgiliz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "funcionario")
public class Colaborador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idColaborador;

    private String nomeColaborador;
    private String CPF;
    private String RG;
    private String classeCarteira;
    private Date dataNascimento;
    private String emailColaborador;
    private String senhaColaborador;
    private Date dataAdmissao;
    private String telefoneColaborador;

    @JsonIgnore
    @OneToMany(mappedBy = "colaborador")
    private List<EnderecoFinal> enderecosFinais;

    @JsonIgnore
    @OneToMany(mappedBy = "colaborador")
    private List<MotoristaDaVez> veiculosFuncionario;

    @JsonIgnore
    @OneToMany(mappedBy = "colaborador")
    private List<Pacote> pacote;

    @JsonIgnore
    @OneToMany(mappedBy = "colaborador")
    private List<Pagamento> pagamentos;
}
