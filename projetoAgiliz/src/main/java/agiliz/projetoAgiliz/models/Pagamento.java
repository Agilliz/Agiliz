package agiliz.projetoAgiliz.models;

import java.util.UUID;

import agiliz.projetoAgiliz.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagamento")
@NoArgsConstructor
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPagamento;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_colaborador")
    private TipoColaborador tipoColaborador;
    
    @ManyToOne
    @JoinColumn(name = "fk_colaborador")
    private Colaborador colaborador;

    private Double remuneracao;

    private TipoPagamento tipoPagamento;

    public Pagamento(int tipoPagamento){
        setTipoPagamento(tipoPagamento);
    }

    public void setTipoPagamento(int tipoPagamento){
        this.tipoPagamento = TipoPagamento.valueOf(tipoPagamento);
    }
}
