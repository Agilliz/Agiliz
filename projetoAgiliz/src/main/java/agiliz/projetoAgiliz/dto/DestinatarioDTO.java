package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DestinatarioDTO(@NotBlank @NotNull String nomeDestinatario,
        @NotBlank @NotNull String ruaDestinatario, @NotBlank @NotNull String cepDestinatario,
        @NotNull Integer numeroDestinatario) {

}
