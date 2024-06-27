package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.comprovanteEntrega.ComprovanteEntregaRequest;
import agiliz.projetoAgiliz.dto.comprovanteEntrega.ComprovanteEntregaResponse;
import agiliz.projetoAgiliz.models.ComprovanteEntrega;
import agiliz.projetoAgiliz.repositories.IComprovanteEntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComprovanteEntregaService {
    private final IComprovanteEntregaRepository comprovanteRepository;
    private final PacoteService pacoteService;

    public ComprovanteEntrega cadastrar(ComprovanteEntregaRequest dto) {
        return null;
    }

    public List<ComprovanteEntregaResponse> getAllResponse() {
        return null;
    }

    public ComprovanteEntrega getPorId(UUID id) {
        return null;
    }

    public ComprovanteEntrega alterar(ComprovanteEntregaRequest dto, UUID id) {
        return null;
    }

    public void deletarPorId(UUID id) {

    }
}
