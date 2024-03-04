package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "multa")
public class MultaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMulta;

    @ManyToOne
    private FuncionarioModel idFuncionario;

    @ManyToOne
    private MotoristaDaVezModel idVeiculoFuncionario;

    @ManyToOne
    private VeiculoModel idVeiculo;

    private DateTimeException dataHora;
    private Double valor;
    private String Infracao;
    private Integer pontuacao;
    private String gravidade;
    private String cep;
    private String rua;
    private int numero;
}
