package agiliz.projetoAgiliz.models;

import agiliz.projetoAgiliz.enums.TipoPagamento;
import agiliz.projetoAgiliz.enums.TipoZona;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "zona")
@NoArgsConstructor
public class Zona implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idZona;

    private String nomeZona;
    private Double valor;
    private Integer limiteSuperiorCEP;
    private Integer limiteInferiorCEP;
    private TipoZona tipoZona;

    public Zona(int tipoZona){
        setTipoZona(tipoZona);
    }

    @OneToMany(mappedBy = "zona")
    private List<Pacote> pacotes;

    public void setTipoZona(int tipoZona){
        this.tipoZona = TipoZona.valueOf(tipoZona);
    }

    @Override
    public String toString() {
        return "Zona{" +
                "idZona=" + idZona +
                ", nomeZona='" + nomeZona + '\'' +
                ", valor=" + valor +
                ", limiteSuperiorCEP=" + limiteSuperiorCEP +
                ", limiteInferiorCEP=" + limiteInferiorCEP +
                ", tipoZona=" + tipoZona +
                '}';
    }
}
