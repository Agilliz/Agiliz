package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "precificacaoZona")
@AllArgsConstructor
@NoArgsConstructor
public class PrecificacaoZona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPrecificacaoZona;

    @ManyToOne
    Unidade fkUnidade;

    private Double preco;

    private int tipoZona;

}
