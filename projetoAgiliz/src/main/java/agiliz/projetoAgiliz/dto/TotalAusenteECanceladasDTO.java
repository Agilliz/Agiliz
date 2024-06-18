package agiliz.projetoAgiliz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalAusenteECanceladasDTO {
    private Long totalAusentes;
    private Long totalCanceladas;
}
