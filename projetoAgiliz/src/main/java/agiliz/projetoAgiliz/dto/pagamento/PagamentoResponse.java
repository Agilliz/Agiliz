package agiliz.projetoAgiliz.dto.pagamento;

import agiliz.projetoAgiliz.models.Pagamento;
import lombok.Getter;


@Getter
public class PagamentoResponse {
    private final String funcionario;
    private final String tipoColaborador;
    private final boolean taxado;
    private final Double remuneracao;
    private final String tipoPagamento;

    public PagamentoResponse(Pagamento pagamento){
        this.funcionario = pagamento.getColaborador().getNomeColaborador();
        this.tipoColaborador = pagamento.getTipoColaborador().getDescricao();
        this.taxado = pagamento.getTipoColaborador().getTaxado();
        this.remuneracao = pagamento.getRemuneracao();
        this.tipoPagamento = pagamento.getTipoPagamento().getAlias();
    }
}
