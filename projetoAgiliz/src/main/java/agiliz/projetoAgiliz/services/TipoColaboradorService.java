package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.TipoColaboradorDTO;
import agiliz.projetoAgiliz.dto.TipoColaboradorResponse;
import agiliz.projetoAgiliz.models.TipoColaborador;
import agiliz.projetoAgiliz.repositories.ITipoColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoColaboradorService {

    @Autowired
    private ITipoColaboradorRepository tipoColaboradorRepository;


    public TipoColaborador inserir(TipoColaboradorDTO dto){
        var tipoColaborador = new TipoColaborador();
        BeanUtils.copyProperties(dto, tipoColaborador);
        tipoColaboradorRepository.save(tipoColaborador);
        return tipoColaborador;
    }

    public List<TipoColaboradorResponse> listar(){
        return tipoColaboradorRepository.findAllTipoColaboradorResponse();
    }
}
