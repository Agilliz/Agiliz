package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.repositories.IZonaRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaService {

    @Autowired
    IZonaRepository repository;

    public Iterable<ZonaModel> listarTodos(){
        return repository.findAll();
    }

    public void cadastrar(ZonaModel zona){
        repository.save(zona);
    }

    public void deletarPorId(UUID idZona){
        repository.deleteById(idZona);
    }
}
