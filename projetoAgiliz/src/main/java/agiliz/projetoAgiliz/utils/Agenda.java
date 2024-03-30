package agiliz.projetoAgiliz.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

@Getter
public abstract class Agenda <T, K>{
    protected Map<K, Timer> agendamentos;

    public Agenda(){
        this.agendamentos = new HashMap<>();
    }

    public abstract void agendar(T tipoAgendamento, K chave, Runnable atividade);

    public void cancelarTarefa(K chave){
        this.agendamentos.get(chave).cancel();
        this.agendamentos.remove(chave);
    }

    public boolean jaEstaAgendado(K chave){
        return this.agendamentos.containsKey(chave);
    }
}
