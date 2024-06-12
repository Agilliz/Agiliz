package agiliz.projetoAgiliz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO {
    private String email;
    private String senha;
    private String token;

    public UsuarioLoginDTO(String email, String senha){
        this.email = email;
        this.senha = senha;
    }
}
