package agiliz.projetoAgiliz.models;

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
public class FuncionarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFuncionario;

    private String nomeFuncionario;
    private String CPF;
    private String RG;
    private String classeCarterira;
    private Date dataNascimento;
    private String emailFuncionario;
    private String senhaFuncionario;
    private Date dataAdmissão;
    private String telefoneFuncionario;

    @OneToMany(mappedBy = "idFuncionario")
    private List<EnderecoFinal> enderecosFinais;

    @OneToMany(mappedBy = "idFuncionario")
    private List<MotoristaDaVezModel> veiculosFuncionario;

    @OneToMany(mappedBy = "idFuncionario")
    private List<MultaModel> multas;

    @OneToMany(mappedBy = "funcionario")
    private List<PacoteModel> pacote;

}
