package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.Veiculo;
import agiliz.projetoAgiliz.repositories.IVeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    private final IVeiculoRepository veiculoRepository;

    public Veiculo getPorId(UUID id) {
        if(!veiculoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return veiculoRepository.findById(id).get();
    }
}
