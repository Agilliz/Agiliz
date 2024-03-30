package agiliz.projetoAgiliz.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CalculadoraDatas {
    public static Date calcularQuintoDiaUtil(LocalDate dataInicio) {
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

    public static Date calcularProximaQuinzena(LocalDate dataInicio){
        return Calendar.getInstance().getTime();
    }

    public static Date calcularProximaSexta(LocalDate dataInicio){
        return Calendar.getInstance().getTime();
    }
}
