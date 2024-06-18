package agiliz.projetoAgiliz.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import agiliz.projetoAgiliz.dto.ColetasPorTempo;
import agiliz.projetoAgiliz.dto.PacoteDTO;
import agiliz.projetoAgiliz.dto.ZonaRanking;
import agiliz.projetoAgiliz.enums.TipoPagamento;
import agiliz.projetoAgiliz.enums.TipoZona;
import agiliz.projetoAgiliz.models.*;
import agiliz.projetoAgiliz.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PacoteService {
    private final IPacoteRepository pacoteRepository;
    private final ZonaService zonaService;
    private final DestinatarioService destinatarioService;
    private final ColaboradorService colaboradorService;
    private final UnidadeService unidadeService;

    public Pacote inserir(PacoteDTO dto) {
        var pacote = new Pacote();
        pacote.setDestinatario(destinatarioService.getPorId(dto.fkDestinatario()));
        pacote.setColaborador(colaboradorService.getPorId(dto.fkFuncionario()));
        pacote.setZona(zonaService.getPorCep(pacote.getDestinatario().getCepDestinatario()));
        pacote.setUnidade(unidadeService.getUnidadePorId(dto.fkUnidade()));
        BeanUtils.copyProperties(dto, pacote);
        return pacoteRepository.save(pacote);
    }

    public List<ZonaRanking> getZonasRankeadas(int numeroEntregas) {
        return pacoteRepository.findZonasRanking(numeroEntregas);
    }

    public List<Pacote> getAllPacoteStatusOnly() {
        return pacoteRepository.findAllPacoteStatusOnly();
    }

    public List<ColetasPorTempo> getColetasPorTempo() {
        return pacoteRepository.findColetasPorTempo();
    }

    public long getQuantidadeColetasRealizadas() {
        return pacoteRepository.countColetasRealizadas().size();
    }

    public long getQuantidadeColetasCanceladas() {
        return pacoteRepository.countColetasCanceladas().size();
    }

    public String getNomeClienteMaiorColeta() {
        return pacoteRepository.findClienteMaiorColeta();
    }

    public String getNomeClienteMenorColeta() {
        return pacoteRepository.findClienteMenorColeta();
    }

    public Pacote atualizar(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

    public Page<Pacote> listarTodos(Pageable pageable){
        return pacoteRepository.findAll(pageable);
    }

    public Optional<Pacote> listarPorId(UUID idPacote){
        return pacoteRepository.findById(idPacote);
    }

    public void deletarPorId(UUID id){
        pacoteRepository.deleteById(id);
    }

    public List<Pacote> listarPacotesParaPagar(Pagamento pagamento) {
        List<Pacote> pacotes = pacoteRepository.findPackagesForPayment(pagamento.getColaborador());

        return pacotes.stream()
                    .filter((pagamento.getTipoPagamento() == TipoPagamento.ZONA_NOVA
                            ? pacote -> pacote.getZona().getTipoZona() == TipoZona.ZONA_NOVA
                            : pacote -> pacote.getZona().getTipoZona() == TipoZona.ZONA_NORMAL))
                    .toList();
    }

    public void confirmarPagamentoEntregas(List<Pacote> pacotes) {
        for(Pacote pacote : pacotes){
            pacote.setPagamentoFeito(true);
            pacoteRepository.save(pacote);
        }
    }
}
