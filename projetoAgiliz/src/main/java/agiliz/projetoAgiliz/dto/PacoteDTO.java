package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.enuns.StatusPacote;
import agiliz.projetoAgiliz.enuns.TipoPacote;
import jakarta.validation.constraints.NotNull;

public record PacoteDTO(@NotNull TipoPacote tipo, @NotNull StatusPacote status) {
}
