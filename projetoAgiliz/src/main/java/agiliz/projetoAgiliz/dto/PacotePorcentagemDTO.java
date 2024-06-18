package agiliz.projetoAgiliz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacotePorcentagemDTO {
    private Double percEntrega;
    private Double percCaminho;
    private Double percColeta;
}
