package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.EmissaoPagamento;
import agiliz.projetoAgiliz.models.Pagamento;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;
import agiliz.projetoAgiliz.repositories.IEmissaoPagamentoRepository;
import agiliz.projetoAgiliz.repositories.IPagamentoRepository;
import agiliz.projetoAgiliz.utils.ContratoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmissaoPagamentoService {
    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private ContratoFactory contratoFactory;

    @Autowired
    private IEmissaoPagamentoRepository emissaoPagamentoRepository;

    public void emitirPagamento(Colaborador colaborador){
        double valor = 0.;
        for(Pagamento pagamento : pagamentoRepository.findByColaborador(colaborador)){
            valor += contratoFactory.gerarContrato(pagamento).efetuarPagamento();
        }

        var emissao = new EmissaoPagamento(valor);
        emissaoPagamentoRepository.save(emissao);
    }
}
