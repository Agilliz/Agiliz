package agiliz.projetoAgiliz.dto.colaborador;

import agiliz.projetoAgiliz.models.Colaborador;
import lombok.Getter;

import java.sql.Date;

@Getter
public class ColaboradorResponse {
    private final String nomeColaborador;
    private final String cpf;
    private final String rg;
    private final String classeCarteira;
    private final Date dataNascimento;
    private final String emailColaborador;
    private final Date dataAdmissao;
    private final String telefoneColaborador;

    public ColaboradorResponse(Colaborador colaborador) {
        this.nomeColaborador = colaborador.getNomeColaborador();
        this.cpf = colaborador.getCpf();
        this.rg = colaborador.getRg();
        this.classeCarteira = colaborador.getClasseCarteira();
        this.dataNascimento = colaborador.getDataNascimento();
        this.emailColaborador = colaborador.getEmailColaborador();
        this.dataAdmissao = colaborador.getDataAdmissao();
        this.telefoneColaborador = colaborador.getTelefoneColaborador();
    }
}
