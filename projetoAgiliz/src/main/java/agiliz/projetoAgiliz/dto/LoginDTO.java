package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private String emailColaborador;
    private String senhaColaborador;
    
    public LoginDTO(Colaborador colaborador) {
        this.emailColaborador = colaborador.getEmailColaborador();
        this.senhaColaborador = colaborador.getSenhaColaborador();
    }

    
}
