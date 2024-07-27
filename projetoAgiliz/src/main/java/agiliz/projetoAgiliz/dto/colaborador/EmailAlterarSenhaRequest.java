package agiliz.projetoAgiliz.dto.colaborador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailAlterarSenhaRequest(
   @NotBlank
   @Email
   String email
) {}
