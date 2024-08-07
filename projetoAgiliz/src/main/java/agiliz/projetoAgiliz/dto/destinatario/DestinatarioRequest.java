package agiliz.projetoAgiliz.dto.destinatario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DestinatarioRequest(@NotBlank @NotNull String nomeDestinatario,
                                  @NotBlank @NotNull String ruaDestinatario, @NotBlank @NotNull String cepDestinatario,
                                  @NotNull Integer numeroDestinatario) {

}
