package agiliz.projetoAgiliz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.dto.MaiorEMenorEntregaDTO;
import agiliz.projetoAgiliz.dto.PacotePorcentagemDTO;
import agiliz.projetoAgiliz.dto.RankingEntregasDTO;

@Service
public class EntregaService {

    @Autowired
    private PacoteService pacoteService;

    @Autowired
    private ColaboradorService colaboradorService;

    public List<PacotePorcentagemDTO> listarPorcentagem(){
        return pacoteService.listarPacotesPorcentagem();
    }

    public List<RankingEntregasDTO> listarRankingEntregas(){
        return pacoteService.listarRankingEntregas();
    }


}
