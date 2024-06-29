package agiliz.projetoAgiliz.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.dto.dashColetas.ColetasPorTempo;
import agiliz.projetoAgiliz.dto.pacote.PacoteRequest;
import agiliz.projetoAgiliz.dto.PacotePorcentagemDTO;
import agiliz.projetoAgiliz.dto.dashEntregas.RankingEntregasDTO;
import agiliz.projetoAgiliz.dto.dashColetas.ZonaRanking;
import agiliz.projetoAgiliz.enums.TipoPagamento;
import agiliz.projetoAgiliz.enums.TipoZona;
import agiliz.projetoAgiliz.models.*;
import agiliz.projetoAgiliz.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    public Pacote inserir(PacoteRequest dto) {
        var pacote = new Pacote();
        associarRelacionamentos(dto, pacote);
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

    public Pacote atualizar(PacoteRequest dto, UUID id) {
        var pacote = listarPorId(id);
        associarRelacionamentos(dto, pacote);
        BeanUtils.copyProperties(dto, pacote);
        return pacoteRepository.save(pacote);
    }

    public List<RankingEntregasDTO> listarRankingEntregas(){
        return pacoteRepository.listarRankingEntregas();
    }
    
    public Page<Pacote> listarTodos(Pageable pageable){
        return pacoteRepository.findAll(pageable);
    }

    public Pacote listarPorId(UUID id){
        if(!pacoteRepository.existsById(id))
            throw new ResponseEntityException(HttpStatusCode.valueOf(404), "Pacote com id listado não existe", 404);

        return pacoteRepository.findById(id).get();
    }

    public void deletarPorId(UUID id){
        if(!pacoteRepository.existsById(id))
            throw new ResponseEntityException(HttpStatusCode.valueOf(404), "Pacote com id listado não existe", 404);

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

    public List<PacotePorcentagemDTO> listarPacotesPorcentagem(){
        return pacoteRepository.listarPorcentagem();
    }

    private void associarRelacionamentos(PacoteRequest dto, Pacote pacote) {
        pacote.setDestinatario(destinatarioService.getPorId(dto.fkDestinatario()));
        pacote.setColaborador(colaboradorService.getPorId(dto.fkFuncionario()));
        pacote.setZona(zonaService.getPorCep(pacote.getDestinatario().getCepDestinatario()));
        pacote.setUnidade(unidadeService.getUnidadePorId(dto.fkUnidade()));
    }
}
