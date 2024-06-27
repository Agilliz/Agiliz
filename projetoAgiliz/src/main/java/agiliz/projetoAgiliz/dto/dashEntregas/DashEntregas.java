package agiliz.projetoAgiliz.dto.dashEntregas;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashEntregas {

    private List<RankingEntregasDTO> rankingEntregas;
    private List<MesPorQtdDeEntregaDTO> mesPorQtdDeEntrega;
    private MaiorEMenorEntrega maiorEMenorEntregaColaborador;
    private TotalAusenteECanceladasDTO totalAusentesECanceladas;
    private TotalEntregaDTO totalEntregaDTO;
    private Long zonasAtendidas;
    
}
