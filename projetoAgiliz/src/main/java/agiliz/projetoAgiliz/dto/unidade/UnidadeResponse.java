package agiliz.projetoAgiliz.dto.unidade;

import agiliz.projetoAgiliz.models.Unidade;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class UnidadeResponse {

    private final String nomeUnidade;
    private final String cnpj;
    private final String rua;
    private final String cep;
    private final Integer numero;
    private final String telefoneUnidade;
    private final Double retornoTotal;
    private final LocalTime horarioCorte;

    public UnidadeResponse(Unidade unidade) {
        this.nomeUnidade = unidade.getNomeUnidade();
        this.cnpj = unidade.getCnpj();
        this.rua = unidade.getRua();
        this.cep = unidade.getCep();
        this.numero = unidade.getNumero();
        this.telefoneUnidade = unidade.getTelefoneUnidade();
        this.retornoTotal = unidade.getRetornoTotal();
        this.horarioCorte = unidade.getHorarioCorte();
    }
}
