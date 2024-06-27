package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.dashColetas.DadosColeta;
import agiliz.projetoAgiliz.enums.StatusPacote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColetasService {

    private final PacoteService pacoteService;
    private final UnidadeService unidadeService;

    public DadosColeta getDadosColeta() {
        var pacotesStatusOnly = pacoteService.getAllPacoteStatusOnly();

        return new DadosColeta(
                pacotesStatusOnly.stream().filter(pacote -> pacote.getStatus() == StatusPacote.ENTREGUE).count(),
                pacotesStatusOnly.stream().filter(pacote -> pacote.getStatus() == StatusPacote.AUSENTE).count(),
                pacotesStatusOnly.stream().filter(pacote -> pacote.getStatus() == StatusPacote.A_CAMINHO).count(),
                pacotesStatusOnly.stream().filter(pacote -> pacote.getStatus() == StatusPacote.EM_ESPERA).count(),
                pacoteService.getQuantidadeColetasRealizadas(),
                pacoteService.getQuantidadeColetasCanceladas(),
                pacoteService.getNomeClienteMenorColeta(),
                pacoteService.getNomeClienteMaiorColeta(),
                unidadeService.getHorarioCorteMedia(),
                pacoteService.getZonasRankeadas(pacotesStatusOnly.size()),
                pacoteService.getColetasPorTempo()
        );
    }
}
