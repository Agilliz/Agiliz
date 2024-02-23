package agiliz.projetoAgiliz.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.models.PacoteModel;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;

@Service
public class PacoteService {
    
    @Autowired
    private IPacoteRepository repository;

    public Iterable<PacoteModel> listarTodos(){
        return repository.findAll();
    }

    public void cadastrar(PacoteModel pacote){
        repository.save(pacote);
    }

    public void deletar(UUID idPacote){
        repository.deleteById(idPacote);
    }
}
