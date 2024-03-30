package agiliz.projetoAgiliz.services;

import java.time.LocalDate;
import java.util.*;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pagamento;
import agiliz.projetoAgiliz.utils.AgendaDePagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class AgendaDeTarefasService {

    @Autowired
    private EmissaoPagamentoService emissaoService;

    @Autowired
    private AgendaDePagamento agendaDePagamento;

    public void agendarEmissaoPagamento(Pagamento pagamento){
        Colaborador colaborador = pagamento.getColaborador();
        UUID idColaborador = colaborador.getIdColaborador();

        if(agendaDePagamento.jaEstaAgendado(idColaborador)) return;

        Runnable tarefa = () -> {
            agendaDePagamento.cancelarTarefa(idColaborador);
            emissaoService.emitirPagamento(colaborador);
            agendarEmissaoPagamento(pagamento);
        };

        agendaDePagamento.agendar(pagamento.getTipoColaborador().getVigencia(), idColaborador, tarefa);
    }

    public void cancelarPagamento(UUID idColaborador){
        agendaDePagamento.cancelarTarefa(idColaborador);
    }
}
