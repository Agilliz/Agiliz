package agiliz.projetoAgiliz.utils;

import agiliz.projetoAgiliz.interfaces.IContrato;

public class ContratoColaboradorInterno implements IContrato{
    private Double salario;

    public ContratoColaboradorInterno(Double salario) {
        this.salario = salario;
    }

    public double efetuarPagamento() {
        return salario;
    }
}
