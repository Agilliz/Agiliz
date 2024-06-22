package agiliz.projetoAgiliz.models;

import agiliz.projetoAgiliz.enums.FrequenciaDespesa;
import agiliz.projetoAgiliz.enums.TipoDespesa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "despesa")
public class Despesa implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID idDespesa;

    private int tipoDespesa;
    private double valorDespesa;
    private int frequenciaDespesa;

    // Ver como será feito a questão do anexo

    public TipoDespesa getTipoDespesa() {
        return TipoDespesa.valueOf(tipoDespesa);
    }

    public FrequenciaDespesa getFrequenciaDespesa() {
        return FrequenciaDespesa.valueOf(frequenciaDespesa);
    }
}
