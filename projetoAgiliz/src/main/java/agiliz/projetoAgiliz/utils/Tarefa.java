package agiliz.projetoAgiliz.utils;

import lombok.Getter;

import java.util.Timer;
import java.util.TimerTask;

@Getter
public class Tarefa extends TimerTask {
    private Runnable acao;
    private Timer timer;

    public Tarefa(Runnable acao){
        this.acao = acao;
        this.timer = new Timer();
    }

    public void run() {
        acao.run();
    }
}
