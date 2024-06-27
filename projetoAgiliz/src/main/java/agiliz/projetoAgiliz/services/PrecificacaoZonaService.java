package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.dto.precificacaoZona.PrecificacaoZonaRequest;
import agiliz.projetoAgiliz.models.PrecificacaoZona;
import agiliz.projetoAgiliz.repositories.IPrecificacaoZona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrecificacaoZonaService {
    @Autowired
    UnidadeService unidadeService;

    @Autowired
    IPrecificacaoZona precificacaoZonaRepository;
    public PrecificacaoZona cadastrar(PrecificacaoZonaRequest precificacaoZonaRequest){
        PrecificacaoZona precificacaoZona = new PrecificacaoZona();
        precificacaoZona.setUnidade(unidadeService.getUnidadePorId(precificacaoZonaRequest.fkUnidade()));
        BeanUtils.copyProperties(precificacaoZonaRequest, precificacaoZona);
        precificacaoZonaRepository.save(precificacaoZona);
        return precificacaoZona;
    }

//    public Page<PrecificacaoZona> listarPrecificacaoZonas(Pageable pageable) {
//        List<PrecificacaoZona> lista = precificacaoZonaRepository.findAll();
//        if (lista.isEmpty()) {
//            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "Não há lista de precificação de zonas", 204);
//        }
//        return precificacaoZonaRepository.findAll(pageable);
//    }


    public PrecificacaoZona listarPrecificacaoZonaID(UUID idPrecificacaoZona){
            return precificacaoZonaRepository.findById(idPrecificacaoZona)
                    .orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Precificação de Zona não encontrada", 404));
    }

    public PrecificacaoZona alterarPrecificacaoZonaPorId(UUID idPrecificacaoZona, PrecificacaoZonaRequest precificacaoZonaRequest) {
        PrecificacaoZona precificacaoZona = listarPrecificacaoZonaID(idPrecificacaoZona);
        BeanUtils.copyProperties(precificacaoZonaRequest, precificacaoZona);
        return precificacaoZonaRepository.save(precificacaoZona);
    }



    public void deletarPrecificacaoZonaPorId(UUID idPrecificacaoZona) {
        if (!precificacaoZonaRepository.findById(idPrecificacaoZona).isPresent()) {
            throw new ResponseEntityException(HttpStatus.NOT_FOUND, "Precificação de Zona não encontrada", 404);
        }
        precificacaoZonaRepository.deleteById(idPrecificacaoZona);
    }

}
