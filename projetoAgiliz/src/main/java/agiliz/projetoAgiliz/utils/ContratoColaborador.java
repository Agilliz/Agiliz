package agiliz.projetoAgiliz.utils;

import agiliz.projetoAgiliz.interfaces.IContrato;
import agiliz.projetoAgiliz.models.Colaborador;

public class ContratoColaborador {
    private String nome;
    private String cpf;
    private String rg;
    private IContrato contrato;

    public ContratoColaborador(Colaborador funcionario, IContrato contrato){
        this.nome = funcionario.getNomeColaborador();
        this.cpf = funcionario.getCpf();
        this.rg = funcionario.getRg();
        this.contrato = contrato;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public Double getPagamento() {
        return contrato.efetuarPagamento();
    }
}
