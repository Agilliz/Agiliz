package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.Zona;
import agiliz.projetoAgiliz.repositories.IZonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ZonaService {
    private final IZonaRepository zonaRepository;

    public Zona getPorId(UUID id) {
        if(!zonaRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return zonaRepository.findById(id).get();
    }

    public Zona getPorCep(String cep) {
        var zonaOpt = zonaRepository.findByCep(Integer.parseInt(cep.substring(0, 5)));
        if(zonaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return zonaOpt.get();
    }
}
