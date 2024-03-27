package agiliz.projetoAgiliz.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "vigencia")
public class Vigencia implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVigencia;

    private Integer dias;
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "vigencia")
    private List<TipoColaborador> tipoColaboradores;

}
