package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.dto.PagamentoDTO;
import agiliz.projetoAgiliz.models.*;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;
import agiliz.projetoAgiliz.repositories.IPagamentoRepository;
import agiliz.projetoAgiliz.repositories.ITipoColaboradorRepository;
import agiliz.projetoAgiliz.utils.ContratoColaborador;
import agiliz.projetoAgiliz.utils.ContratoFactory;
import agiliz.projetoAgiliz.utils.FolhaDePagamento;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoService {
    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private IColaboradorRepository funcionarioRepository;

    @Autowired
    private ITipoColaboradorRepository tipoColaboradorRepositoty;

    @Autowired
    private ContratoFactory contratoFactory;

    public List<Pagamento> listar(){
        return pagamentoRepository.findAll();
    }

    public Pagamento listarPorId(UUID id){
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        if(pagamento.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado");
        return pagamento.get();
    }

    public Pagamento cadastrar(PagamentoDTO dto){
        return pagamentoRepository.save(gerarPagamento(dto));
    }

    private Pagamento gerarPagamento(PagamentoDTO dto){
        var pagamento = new Pagamento(dto.tipoPagamento());
        BeanUtils.copyProperties(dto, pagamento);
        pagamento.setTipoColaborador(retornarTipoColaborador(dto.fkTipoColaborador()));
        pagamento.setColaborador(retornarFuncionario(dto.fkFuncionario()));
        //agenda.agendarEmissaoPagamento(pagamento);
        return pagamento;
    }

    private Colaborador retornarFuncionario(UUID id){
        Optional<Colaborador> funcionario = funcionarioRepository.findById(id);

        if(funcionario.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado");

        return funcionario.get();
    }

    private TipoColaborador retornarTipoColaborador(UUID id){
        Optional<TipoColaborador> tipoColaborador = tipoColaboradorRepositoty.findById(id);

        if(tipoColaborador.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de colaborador não encontrado");

        return tipoColaborador.get();
    }

    public FolhaDePagamento gerarFolhaDePagamento(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        List<ContratoColaborador> colaboradores = pagamentos.stream()
                .map(pagamento -> new ContratoColaborador(
                        pagamento.getColaborador(),
                        contratoFactory.gerarContrato(pagamento)
                ))
                .toList();

        return new FolhaDePagamento(colaboradores);
    }
}
