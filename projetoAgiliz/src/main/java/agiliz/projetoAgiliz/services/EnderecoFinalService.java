package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.dto.EnderecoFinalDTO;
import agiliz.projetoAgiliz.dto.PrecificacaoZonaDTO;
import agiliz.projetoAgiliz.dto.PrecificacaoZonaResponse;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.EnderecoFinal;
import agiliz.projetoAgiliz.models.PrecificacaoZona;
import agiliz.projetoAgiliz.repositories.IEnderecoFinalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnderecoFinalService {
    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private IEnderecoFinalRepository enderecoFinalRepository;

    public EnderecoFinal cadastrar(EnderecoFinalDTO enderecoFinalDTO) {
            var enderecoFinal = new EnderecoFinal();
            enderecoFinal.setColaborador(colaboradorService.getPorId(enderecoFinalDTO.fkFuncionario()));
            BeanUtils.copyProperties(enderecoFinalDTO, enderecoFinal);
            enderecoFinalRepository.save(enderecoFinal);
            return enderecoFinal;
    }

    public EnderecoFinal listarEnderecoFinalID(UUID idEnderecoFinal){
        return enderecoFinalRepository.findById(idEnderecoFinal)
                .orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Endereco Final não encontrada", 404));
    }

    public EnderecoFinal alterarEnderecoFinalPorId(UUID idEnderecoFinal, EnderecoFinalDTO enderecoFinalDTO) {
        EnderecoFinal enderecoFinal = listarEnderecoFinalID(idEnderecoFinal);
        BeanUtils.copyProperties(enderecoFinalDTO, enderecoFinal);
        return enderecoFinalRepository.save(enderecoFinal);
    }

    public void deletarEnderecoFinalPorId(UUID idEnderecoFinal) {
        if (!enderecoFinalRepository.findById(idEnderecoFinal).isPresent()) {
            throw new ResponseEntityException(HttpStatus.NOT_FOUND, "Endereco Final de Zona não encontrada", 404);
        }
        enderecoFinalRepository.deleteById(idEnderecoFinal);
    }

}
