package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.DespesaDTO;
import agiliz.projetoAgiliz.enums.TipoDespesa;
import agiliz.projetoAgiliz.models.Despesa;
import agiliz.projetoAgiliz.models.Ipva;
import agiliz.projetoAgiliz.repositories.IDespesaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class DespesaService {
    private final IDespesaRepository despesaRepository;
    private final VeiculoService veiculoService;

    public Despesa inserir(DespesaDTO dto){
        var despesa = TipoDespesa.valueOf(dto.tipoDespesa()) == TipoDespesa.IPVA
                ? new Ipva()
                : new Despesa();

        if(despesa instanceof Ipva ipva) ipva.setVeiculo(veiculoService.getPorId(dto.fkVeiculo()));
        BeanUtils.copyProperties(dto, despesa);
        return despesaRepository.save(despesa);
    }

}
