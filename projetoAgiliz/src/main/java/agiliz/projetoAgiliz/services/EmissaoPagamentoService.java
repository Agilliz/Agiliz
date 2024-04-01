package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.EmissaoPagamento;
import agiliz.projetoAgiliz.models.Pagamento;

import agiliz.projetoAgiliz.repositories.IEmissaoPagamentoRepository;
import agiliz.projetoAgiliz.repositories.IPagamentoRepository;
import agiliz.projetoAgiliz.utils.ContratoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmissaoPagamentoService {
    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private ContratoFactory contratoFactory;

    @Autowired
    private IEmissaoPagamentoRepository emissaoRepository;

    public void emitirPagamento(Pagamento pagamento){
        double valor = contratoFactory.gerarContrato(pagamento).efetuarPagamento();
        if(valor == 0) return;

        Colaborador colaborador = pagamento.getColaborador();

        Optional<EmissaoPagamento> emissaoCadastrada = emissaoRepository
                .findByColaboradorAndFechada(colaborador, false);

        EmissaoPagamento emissao;

        if(emissaoCadastrada.isEmpty()) emissao = new EmissaoPagamento(valor, colaborador);

        else{
            emissao = emissaoCadastrada.get();
            emissao.atualizarValor(valor);
        }

        emissaoRepository.save(emissao);
    }

    public List<EmissaoPagamento> listar() {
        return emissaoRepository.findAll();
    }
}
