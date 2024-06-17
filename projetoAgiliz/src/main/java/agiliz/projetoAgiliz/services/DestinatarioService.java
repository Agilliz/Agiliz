package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.Destinatario;
import agiliz.projetoAgiliz.repositories.IDestinatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DestinatarioService {
    private final IDestinatarioRepository repository;

    public Destinatario getPorId(UUID id) {
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return repository.findById(id).get();
    }
}
