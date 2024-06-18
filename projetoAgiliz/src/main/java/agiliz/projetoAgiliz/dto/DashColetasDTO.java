package agiliz.projetoAgiliz.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashColetasDTO {

    private List<RankingEntregasDTO> rankingEntregas;
    private List<MesPorQtdDeEntregaDTO> mesPorQtdDeEntrega;
    private MaiorEMenorEntregaDTO maiorEMenorEntregaColaborador;
    private TotalAusenteECanceladasDTO totalAusentesECanceladas;
    private TotalEntregaDTO totalEntregaDTO;
    private Long zonasAtendidas;
    
}
