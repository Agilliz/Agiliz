package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.dto.PrecificacaoZonaDTO;
import agiliz.projetoAgiliz.dto.PrecificacaoZonaResponse;
import agiliz.projetoAgiliz.models.PrecificacaoZona;
import agiliz.projetoAgiliz.models.Unidade;
import agiliz.projetoAgiliz.repositories.IPrecificacaoZona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PrecificacaoZonaService {
    @Autowired
    UnidadeService unidadeService;

    @Autowired
    IPrecificacaoZona precificacaoZonaRepository;
    public PrecificacaoZona cadastrar(PrecificacaoZonaDTO precificacaoZonaDTO){
        PrecificacaoZona precificacaoZona = new PrecificacaoZona();
        precificacaoZona.setUnidade(unidadeService.getUnidadePorId(precificacaoZonaDTO.fkUnidade()));
        BeanUtils.copyProperties(precificacaoZonaDTO, precificacaoZona);
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

    public PrecificacaoZona alterarPrecificacaoZonaPorId(UUID idPrecificacaoZona, PrecificacaoZonaDTO precificacaoZonaDTO) {
        PrecificacaoZona precificacaoZona = listarPrecificacaoZonaID(idPrecificacaoZona);
        BeanUtils.copyProperties(precificacaoZonaDTO, precificacaoZona);
        return precificacaoZonaRepository.save(precificacaoZona);
    }



    public void deletarPrecificacaoZonaPorId(UUID idPrecificacaoZona) {
        if (!precificacaoZonaRepository.findById(idPrecificacaoZona).isPresent()) {
            throw new ResponseEntityException(HttpStatus.NOT_FOUND, "Precificação de Zona não encontrada", 404);
        }
        precificacaoZonaRepository.deleteById(idPrecificacaoZona);
    }

}
