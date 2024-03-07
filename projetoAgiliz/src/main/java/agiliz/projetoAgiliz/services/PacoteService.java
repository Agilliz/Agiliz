package agiliz.projetoAgiliz.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.models.DestinatarioModel;
import agiliz.projetoAgiliz.models.PacoteModel;
import agiliz.projetoAgiliz.models.ZonaModel;
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

    public PacoteModel salvar(PacoteModel pacote) {
        return pacoteRepository.save(pacote);
    }

    public Page<PacoteModel> listarTodos(Pageable pageable){
        return pacoteRepository.findAll(pageable);
    }

    public Optional<PacoteModel> listarPorId(UUID idPacote){
        return pacoteRepository.findById(idPacote);
    }

    public void deletarPorId(UUID id){
        pacoteRepository.deleteById(id);
    }

    public void associarZona(PacoteModel pacote) {
        int cep = Integer.parseInt(pacote.getDestinatario().getCepDestinatario().substring(0, 5));
        System.out.println(cep);
        ZonaModel zona = zonaRepository.findByCep(cep);
        pacote.setZona(zona);
    }

    public void associarDestinatario(UUID idDestinatario, PacoteModel pacote){
        Optional<DestinatarioModel> destinatarioOpt = destinatarioRepository.findById(idDestinatario);

        if(destinatarioOpt.isEmpty()) return;

        var destinatario = destinatarioOpt.get();

        pacote.setDestinatario(destinatario);
    }    
}
