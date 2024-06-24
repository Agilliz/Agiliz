package agiliz.projetoAgiliz.models;

import agiliz.projetoAgiliz.enums.FrequenciaDespesa;
import agiliz.projetoAgiliz.enums.TipoDespesa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "despesa")
public class Despesa implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID idDespesa;

    private int tipoDespesa;
    private double valorDespesa;
    private int frequenciaDespesa;

    @ManyToOne
    @JoinColumn(name = "fk_veiculo")
    private Veiculo veiculo;

    // Ver como será feito a questão do anexo


    public Despesa(int tipoDespesa, double valorDespesa, int frequenciaDespesa) {
        this.tipoDespesa = tipoDespesa;
        this.valorDespesa = valorDespesa;
        this.frequenciaDespesa = frequenciaDespesa;
    }

    public TipoDespesa getTipoDespesa() {
        return TipoDespesa.valueOf(tipoDespesa);
    }

    public FrequenciaDespesa getFrequenciaDespesa() {
        return FrequenciaDespesa.valueOf(frequenciaDespesa);
    }
}
