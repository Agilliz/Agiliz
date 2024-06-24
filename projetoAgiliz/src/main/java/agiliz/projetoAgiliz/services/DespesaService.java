package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.DespesaDTO;
import agiliz.projetoAgiliz.dto.DespesaResponse;
import agiliz.projetoAgiliz.enums.TipoDespesa;
import agiliz.projetoAgiliz.models.Despesa;
import agiliz.projetoAgiliz.repositories.IDespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DespesaService {
    private final IDespesaRepository despesaRepository;
    private final VeiculoService veiculoService;

    public List<Despesa> getAll() {
        return despesaRepository.findAll();
    }

    public List<DespesaResponse> getAllResponse() {
        return despesaRepository.findAll().stream()
                .map(DespesaResponse::new)
                .toList();
    }

    public Despesa getPorId(UUID id) {
        if(!despesaRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado a despesa com o id especificado");

        return despesaRepository.findById(id).get();
    }

    public Despesa inserir(DespesaDTO dto){
        var despesa = new Despesa();

        if(TipoDespesa.valueOf(dto.tipoDespesa()) == TipoDespesa.IPVA){
            if(dto.fkVeiculo() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            despesa.setVeiculo(veiculoService.getPorId(dto.fkVeiculo()));
        }

        BeanUtils.copyProperties(dto, despesa);
        return despesaRepository.save(despesa);
    }

    public Despesa alterar(DespesaDTO dto, UUID idDespesa) {
        var despesa = getPorId(idDespesa);
        BeanUtils.copyProperties(dto, despesa);

        if(TipoDespesa.valueOf(dto.tipoDespesa()) == TipoDespesa.IPVA){
            if(dto.fkVeiculo() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            despesa.setVeiculo(veiculoService.getPorId(dto.fkVeiculo()));
        }else if(despesa.getTipoDespesa() == TipoDespesa.IPVA) {
            despesa.setVeiculo(null);
        }

        return despesaRepository.save(despesa);
    }

    public void deletarPorId(UUID id) {
        if(!despesaRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        despesaRepository.deleteById(id);
    }
}
