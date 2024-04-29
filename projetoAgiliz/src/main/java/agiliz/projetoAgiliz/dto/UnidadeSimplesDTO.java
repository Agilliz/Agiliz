package agiliz.projetoAgiliz.dto;

import java.util.List;
import java.util.UUID;

import agiliz.projetoAgiliz.models.Fornecedor;
import agiliz.projetoAgiliz.models.Pacote;
import agiliz.projetoAgiliz.models.Unidade;

public class UnidadeSimplesDTO{
    private UUID idUnidade;
    private List<Pacote> pacotes;
    private String rua;
    private String cep;
    private Integer numero;
    private String digitosVerificadores;
    private String telefoneUnidade;
    
    public UnidadeSimplesDTO(Unidade unidade) {
        this.idUnidade = unidade.getIdUnidade();
        this.pacotes = unidade.getPacotes();
        this.rua = unidade.getRua();
        this.cep = unidade.getCep();
        this.numero = unidade.getNumero();
        this.digitosVerificadores = unidade.getDigitosVerificadores();
        this.telefoneUnidade = unidade.getTelefoneUnidade();
    }

}
