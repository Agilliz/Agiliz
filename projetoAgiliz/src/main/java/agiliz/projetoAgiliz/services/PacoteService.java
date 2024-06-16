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
    private final IZonaRepository zonaRepository;
    private final IDestinatarioRepository destinatarioRepository;
    private final IColaboradorRepository funcionarioRepository;
    private final UnidadeService unidadeService;

    public Pacote inserir(PacoteDTO dto) {
        var pacote = new Pacote();
        BeanUtils.copyProperties(dto, pacote);
        associarDestinatario(dto.fkDestinatario(), pacote);
        associarFuncionario(dto.fkFuncionario(), pacote);
        associarZona(pacote);
        associarUnidade(dto.fkUnidade(), pacote);
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
        return pacoteRepository.countColetasRealizadas();
    }

    public long getQuantidadeColetasCanceladas() {
        return pacoteRepository.countColetasCanceladas();
    }

    public String getNomeClienteMaiorColeta() {
        return pacoteRepository.findClienteMaiorColeta();
    }

    public String getNomeClienteMenorColeta() {
        return pacoteRepository.findClienteMenorColeta();
    }

    private void associarUnidade(UUID fkUnidade, Pacote pacote) {
        var unidade = unidadeService.getUnidadePorId(fkUnidade);
        unidadeService.contabilizarRetornoTotal(unidade, pacote.getZona().getValor());
        pacote.setUnidade(unidade);
    }

    private void associarFuncionario(UUID fkFuncionario, Pacote pacote) {
        Optional<Colaborador> funcionarioOpt = funcionarioRepository.findById(fkFuncionario);
        if(funcionarioOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        pacote.setColaborador(funcionarioOpt.get());
    }

    public Pacote atualizar(Pacote pacote){
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

    private void associarZona(Pacote pacote) {
        int cep = Integer.parseInt(pacote.getDestinatario().getCepDestinatario().substring(0, 5));
        Zona zona = zonaRepository.findByCep(cep);
        pacote.setZona(zona);
    }

    private void associarDestinatario(UUID idDestinatario, Pacote pacote){
        var destinatarioOpt = destinatarioRepository.findById(idDestinatario);
        if(destinatarioOpt.isEmpty()) return;
        var destinatario = destinatarioOpt.get();
        pacote.setDestinatario(destinatario);
    }

    public List<Pacote> listarPacotesParaPagar(Pagamento pagamento){
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
