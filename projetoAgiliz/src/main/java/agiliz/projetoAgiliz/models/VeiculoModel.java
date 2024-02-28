package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Veiculo")
public class VeiculoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVeiculo;
    private String tipoVeiculo;
    private String proprietario;
    private String placa;

    @OneToMany(mappedBy = "idFuncionario")
    private List<FuncionarioModel> funcionario;
}
