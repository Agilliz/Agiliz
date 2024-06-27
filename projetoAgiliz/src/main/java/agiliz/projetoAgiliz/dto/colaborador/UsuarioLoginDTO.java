package agiliz.projetoAgiliz.dto.colaborador;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    private String token;

    public UsuarioLoginDTO(String email, String senha){
        this.email = email;
        this.senha = senha;
    }
}
