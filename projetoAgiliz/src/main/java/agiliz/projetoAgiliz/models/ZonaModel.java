package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="zone")
public class ZonaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idZona;

    private String nomeZona;
    private Double valor;
    private Integer limiteSuperiorCEP;
    private Integer limiteInferiorCEP;

    @OneToMany(mappedBy = "pacote")
    private List<PacoteModel> pacotes;
}
