package agiliz.projetoAgiliz.utils;

import agiliz.projetoAgiliz.enums.TipoPagamento;
import agiliz.projetoAgiliz.interfaces.IContrato;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pacote;
import agiliz.projetoAgiliz.models.Pagamento;
import agiliz.projetoAgiliz.services.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoFactory {

    @Autowired
    private PacoteService pacoteService;

    public IContrato gerarContrato(Pagamento pagamento){
        boolean colaboradorTaxado = pagamento.getTipoColaborador().getTaxado();
        if(colaboradorTaxado) return montarContratoTaxado(pagamento);
        return new ContratoColaboradorInterno(pagamento.getRemuneracao());
    }

    private ContratoColaboradorExterno montarContratoTaxado(Pagamento pagamento){
        List<Pacote> pacotes = pacoteService.listarPacotesParaPagar(pagamento);
        pacoteService.confirmarPagamentoEntregas(pacotes);
        return new ContratoColaboradorExterno(pacotes.size(), pagamento.getRemuneracao());
    }
}
