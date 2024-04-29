package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Colaborador;
import lombok.Getter;

import java.sql.Date;

@Getter
public class ColaboradorResponse {
    private final String nomeColaborador;
    private final String CPF;
    private final String RG;
    private final String classeCarteira;
    private final Date dataNascimento;
    private final String emailColaborador;
    private final String senhaColaborador;
    private final Date dataAdmissao;
    private final String telefoneColaborador;

    public ColaboradorResponse(Colaborador colaborador) {
        this.nomeColaborador = colaborador.getNomeColaborador();
        this.CPF = colaborador.getCPF();
        this.RG = colaborador.getRG();
        this.classeCarteira = colaborador.getClasseCarteira();
        this.dataNascimento = colaborador.getDataNascimento();
        this.emailColaborador = colaborador.getEmailColaborador();
        this.senhaColaborador = colaborador.getSenhaColaborador();
        this.dataAdmissao = colaborador.getDataAdmissao();
        this.telefoneColaborador = colaborador.getTelefoneColaborador();
    }
}
