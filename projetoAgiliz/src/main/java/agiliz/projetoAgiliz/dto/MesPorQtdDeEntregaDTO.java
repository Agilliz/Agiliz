package agiliz.projetoAgiliz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MesPorQtdDeEntregaDTO {
    private Integer mes;
    private Long qtdEntregas;
}
