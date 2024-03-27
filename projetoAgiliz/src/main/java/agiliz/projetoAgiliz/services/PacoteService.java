package agiliz.projetoAgiliz.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import agiliz.projetoAgiliz.dto.PacoteDTO;
import agiliz.projetoAgiliz.enums.StatusPacote;
import agiliz.projetoAgiliz.enums.TipoZona;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pacote;
import agiliz.projetoAgiliz.models.Zona;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.models.Destinatario;
import agiliz.projetoAgiliz.repositories.IDestinatarioRepository;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;
import agiliz.projetoAgiliz.repositories.IZonaRepository;

@Service
public class PacoteService {
    @Autowired
    private IPacoteRepository pacoteRepository;

    @Autowired
    private IZonaRepository zonaRepository;

    @Autowired
    private IDestinatarioRepository destinatarioRepository;

    @Autowired
    private IColaboradorRepository funcionarioRepository;

    public Pacote inserir(PacoteDTO pacoteDTO) {
        var pacote = new Pacote();
        BeanUtils.copyProperties(pacoteDTO, pacote);
        associarDestinatario(pacoteDTO.fkDestinatario(), pacote);
        associarFuncionario(pacoteDTO.fkFuncionario(), pacote);
        associarZona(pacote);
        return pacoteRepository.save(pacote);
    }

    private void associarFuncionario(UUID fkFuncionario, Pacote pacote) {
        Optional<Colaborador> funcionarioOpt = funcionarioRepository.findById(fkFuncionario);
        if(funcionarioOpt.isEmpty()) return;
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
        Optional<Destinatario> destinatarioOpt = destinatarioRepository.findById(idDestinatario);
        if(destinatarioOpt.isEmpty()) return;
        var destinatario = destinatarioOpt.get();
        pacote.setDestinatario(destinatario);
    }

    public List<Pacote> listarPorIdFuncionario(UUID id){
        return pacoteRepository.findByIdFuncionario(id);
    }

    public List<Pacote> consultarPacotesEntreguesZonaNova(UUID id){
        return listarPorIdFuncionario(id).stream()
                .filter(pacote -> pacote.getStatus() == StatusPacote.ENTREGUE &&
                        pacote.getZona().getTipoZonaEnum() == TipoZona.ZONA_NOVA)
                .toList();
    }

    public List<Pacote> consultarPacotesEntreguesZonaNormal(UUID id){
        return listarPorIdFuncionario(id).stream()
                .filter(pacote -> pacote.getStatus() == StatusPacote.ENTREGUE &&
                        pacote.getZona().getTipoZonaEnum() == TipoZona.ZONA_NORMAL)
                .toList();
    }
}
