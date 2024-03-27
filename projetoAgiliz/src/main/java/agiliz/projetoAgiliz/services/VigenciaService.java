package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.VigenciaDTO;
import agiliz.projetoAgiliz.models.Vigencia;
import agiliz.projetoAgiliz.repositories.IVigenciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VigenciaService {

    @Autowired
    private IVigenciaRepository vigenciaRepository;

    public Vigencia inserir(VigenciaDTO vigenciaDTO){
        var vigencia = new Vigencia();
        BeanUtils.copyProperties(vigenciaDTO, vigencia);
        return vigenciaRepository.save(vigencia);
    }

    public List<Vigencia> listar(){
        return vigenciaRepository.findAll();
    }

    public Vigencia listarPorId(UUID idVigencia){
        return vigenciaRepository.findById(idVigencia).orElse(null);
    }
}
