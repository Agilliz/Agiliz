package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.repositories.IZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ZonaService {

    @Autowired
    IZonaRepository repository;

    public Iterable<ZonaModel> listarZonas(){
        return repository.findAll();
    }
}
