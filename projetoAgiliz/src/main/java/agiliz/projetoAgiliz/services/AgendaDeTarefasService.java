package agiliz.projetoAgiliz.services;

import java.time.LocalDate;
import java.util.*;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class AgendaDeTarefasService {

    @Autowired
    private EmissaoPagamentoService emissaoService;

    private Map<UUID, Timer> agendaDeTarefas = new HashMap<>();

    public void agendarEmissaoPagamento(Pagamento pagamento){
        Colaborador colaborador = pagamento.getColaborador();
        if(agendaDeTarefas.containsKey(colaborador.getIdColaborador())) return;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                agendaDeTarefas.remove(colaborador.getIdColaborador());
                emissaoService.emitirPagamento(colaborador);
                agendarEmissaoPagamento(pagamento);
            }
        };

        int vigencia = pagamento.getTipoColaborador().getVigencia().getDias();
        Timer timer = new Timer();
        timer.schedule(task, 15_000);
        agendaDeTarefas.put(colaborador.getIdColaborador(), timer);
    }

    public Date getQuintoDiaUtil(LocalDate dataInicio) {
        Calendar instanciaCalendario = Calendar.getInstance();
        instanciaCalendario.set(Calendar.YEAR, dataInicio.getYear());
        instanciaCalendario.set(Calendar.MONTH, dataInicio.getMonth().getValue());
        instanciaCalendario.set(Calendar.DAY_OF_MONTH, 1);
        instanciaCalendario.set(Calendar.HOUR, 0);
        instanciaCalendario.set(Calendar.MINUTE, 0);
        instanciaCalendario.set(Calendar.SECOND, 0);
        instanciaCalendario.set(Calendar.MILLISECOND, 0);

        int dia = 0;
        int diasUteis = 1;
        int diaInicial = instanciaCalendario.get(Calendar.DAY_OF_MONTH);
        while(
            diasUteis <= 5 ||
            instanciaCalendario.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
            instanciaCalendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
        ){
            int diaAtual = diaInicial + dia;
            instanciaCalendario.set(Calendar.DAY_OF_MONTH, diaAtual);

            boolean diaUtil = instanciaCalendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
                    instanciaCalendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;

            if(diaUtil) diasUteis++;
            dia++;
        }

        return instanciaCalendario.getTime();
    }
}
