package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.dto.comprovanteEntrega.ComprovanteEntregaRequest;
import agiliz.projetoAgiliz.dto.comprovanteEntrega.ComprovanteEntregaResponse;
import agiliz.projetoAgiliz.models.ComprovanteEntrega;
import agiliz.projetoAgiliz.repositories.IComprovanteEntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComprovanteEntregaService {
    private final IComprovanteEntregaRepository comprovanteRepository;
    private final PacoteService pacoteService;

    public ComprovanteEntrega cadastrar(ComprovanteEntregaRequest dto) {
        var comprovante = new ComprovanteEntrega();
        comprovante.setPacote(pacoteService.listarPorId(dto.fkPacote()));
        BeanUtils.copyProperties(dto, comprovante);
        return comprovanteRepository.save(comprovante);
    }

    public List<ComprovanteEntregaResponse> getAllResponse() {
        return comprovanteRepository.findAllResponse();
    }

    public ComprovanteEntrega getPorId(UUID id) {
        if(!comprovanteRepository.existsById(id))
            throw new ResponseEntityException(HttpStatusCode.valueOf(404), "Comprovante com id listado não existe", 404);

        return comprovanteRepository.findById(id).get();
    }

    public ComprovanteEntrega alterar(ComprovanteEntregaRequest dto, UUID id) {
        var comprovante = getPorId(id);
        comprovante.setPacote(pacoteService.listarPorId(dto.fkPacote()));
        BeanUtils.copyProperties(dto, comprovante);
        return comprovanteRepository.save(comprovante);
    }

    public void deletarPorId(UUID id) {
        if(!comprovanteRepository.existsById(id))
            throw new ResponseEntityException(HttpStatusCode.valueOf(404), "Comprovante com id listado não existe", 404);

        comprovanteRepository.deleteById(id);
    }
}
