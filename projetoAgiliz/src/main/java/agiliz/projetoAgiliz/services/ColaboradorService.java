package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.ColaboradorDTO;
import agiliz.projetoAgiliz.dto.ColaboradorResponse;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {
    @Autowired
    private IColaboradorRepository colaboradorRepository;

    @Autowired
    private AgendaDeTarefasService agenda;

    public Colaborador inserir(ColaboradorDTO dto){
        var colaborador = new Colaborador();
        BeanUtils.copyProperties(dto, colaborador);
        colaboradorRepository.save(colaborador);
        return colaborador;
    }

    public List<ColaboradorResponse> listarTodos(Pageable pageable) {
        return colaboradorRepository.findAllColaboradorResponse();
    }
}
