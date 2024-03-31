package agiliz.projetoAgiliz.utils;

import agiliz.projetoAgiliz.enums.Vigencia;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Timer;
import java.util.UUID;

@Service
@Getter
public class AgendaDePagamento extends Agenda<Vigencia, UUID> {
    public AgendaDePagamento(){
        super();
    }

    public void agendar(Vigencia vigencia, UUID idColaborador, Runnable atividade) {
        Timer timer = new Timer();
//        timer.schedule(new Tarefa(atividade), getDataExecucao(vigencia));
        timer.schedule(new Tarefa(atividade), 15_000);
        this.agendamentos.put(idColaborador, timer);
    }

    private Date getDataExecucao(Vigencia vigencia){
        return switch (vigencia){
            case MENSAL -> CalculadoraDatas.calcularQuintoDiaUtil(LocalDate.now());
            case QUINZENAL -> CalculadoraDatas.calcularProximaQuinzena(LocalDate.now());
            case SEMANAL -> CalculadoraDatas.calcularProximaSexta(LocalDate.now());
        };
    }
}
