package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "frota")
public class MotoristaDaVezModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVeiculosFuncionario;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private FuncionarioModel idFuncionario;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private VeiculoModel idVeiculo;

    private Date Data;

    @ManyToOne
    @JoinColumn(name = "id_multas")
    private MultaModel multa;


}
