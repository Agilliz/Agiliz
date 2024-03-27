package agiliz.projetoAgiliz.utils;

import agiliz.projetoAgiliz.enums.TipoPagamento;
import agiliz.projetoAgiliz.interfaces.IContrato;
import agiliz.projetoAgiliz.models.Pagamento;
import agiliz.projetoAgiliz.services.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        UUID idFuncionario = pagamento.getColaborador().getIdColaborador();

        int numeroDeEntregas = (pagamento.getTipoPagamentoEnum() == TipoPagamento.ZONA_NORMAL)
                ? pacoteService.consultarPacotesEntreguesZonaNormal(idFuncionario).size()
                : pacoteService.consultarPacotesEntreguesZonaNova(idFuncionario).size();

        return new ContratoColaboradorExterno(numeroDeEntregas, pagamento.getRemuneracao());
    }
}
