package agiliz.projetoAgiliz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import agiliz.projetoAgiliz.models.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private String emailColaborador;
    @JsonIgnore
    private String senhaColaborador;
    
    public LoginDTO(Colaborador colaborador) {
        this.emailColaborador = colaborador.getEmailColaborador();
        this.senhaColaborador = colaborador.getSenhaColaborador();
    }

    
}
