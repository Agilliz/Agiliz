package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.EmissaoPagamento;
import agiliz.projetoAgiliz.models.Pagamento;

import agiliz.projetoAgiliz.repositories.IEmissaoPagamentoRepository;
import agiliz.projetoAgiliz.repositories.IPagamentoRepository;
import agiliz.projetoAgiliz.utils.ContratoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmissaoPagamentoService {
    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private ContratoFactory contratoFactory;

    @Autowired
    private IEmissaoPagamentoRepository emissaoPagamentoRepository;

    public void emitirPagamento(Pagamento pagamento){
        double valor = contratoFactory.gerarContrato(pagamento).efetuarPagamento();
        if(valor == 0) return;
        var emissao = new EmissaoPagamento(valor, pagamento.getColaborador());
        emissaoPagamentoRepository.save(emissao);
    }
}
