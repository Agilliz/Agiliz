package agiliz.projetoAgiliz.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import agiliz.projetoAgiliz.enums.Vigencia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_colaborador")
public class TipoColaborador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTipoColaborador;
    private Boolean taxado;
    private String descricao;
    private int vigencia;

    public Vigencia getVigencia(){
        return Vigencia.valueOf(this.vigencia);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tipoColaborador")
    private List<Pagamento> pagamentos;
}
