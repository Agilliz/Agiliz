package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.DespesaDTO;
import agiliz.projetoAgiliz.models.Despesa;
import agiliz.projetoAgiliz.repositories.IDespesaRepository;
import org.hibernate.query.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class DespesaService {
    @Autowired
    private IDespesaRepository despesaRepository;

    public Despesa inserir(DespesaDTO dto){
        var despesa = new Despesa();
        BeanUtils.copyProperties(dto, despesa);
        despesaRepository.save(despesa);
        return  despesa;
    }
//    public  Page<Despesa> listarDespesas(Pageable pageable){return  despesaRepository.findAll(pageable);}
}
