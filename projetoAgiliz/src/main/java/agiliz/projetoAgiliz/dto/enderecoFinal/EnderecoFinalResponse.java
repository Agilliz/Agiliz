package agiliz.projetoAgiliz.dto.enderecoFinal;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.EnderecoFinal;
import lombok.Getter;

@Getter
public class EnderecoFinalResponse{

    private final Colaborador colaborador;
    private final String apelido;
    private final String cep;
    private final String rua;
    private final int numero;

    public EnderecoFinalResponse(EnderecoFinal enderecoFinal ){
        this.colaborador = enderecoFinal.getColaborador();
        this.apelido = enderecoFinal.getApelido();
        this.cep = enderecoFinal.getCep();
        this.rua = enderecoFinal.getRua();
        this.numero = enderecoFinal.getNumero();


    }
}
