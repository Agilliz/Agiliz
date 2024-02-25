package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.enums.StatusPacote;
import agiliz.projetoAgiliz.enums.TipoPacote;
import jakarta.validation.constraints.NotNull;

public record PacoteDTO(@NotNull TipoPacote tipo, @NotNull StatusPacote status) {
}
