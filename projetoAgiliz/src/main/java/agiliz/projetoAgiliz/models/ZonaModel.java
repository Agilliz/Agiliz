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
@Table(name = "zona")
public class ZonaModel implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idZona;

    private String nomeZona;
    private Double valor;
    private Integer limiteSuperiorCEP;
    private Integer limiteInferiorCEP;

    @OneToMany
    @JoinColumn(name = "fk_zona")
    private List<PacoteModel> pacotes;
}
