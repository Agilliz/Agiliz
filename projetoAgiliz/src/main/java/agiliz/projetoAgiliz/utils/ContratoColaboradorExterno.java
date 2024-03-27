package agiliz.projetoAgiliz.utils;

import agiliz.projetoAgiliz.interfaces.IContrato;

public class ContratoColaboradorExterno implements IContrato{

    private final Integer entregas;
    private final Double taxa;

    public ContratoColaboradorExterno(Integer entregas, Double taxa) {
        this.entregas = entregas;
        this.taxa = taxa;
    }

    public double efetuarPagamento() {
        return entregas * taxa;
    }
}
