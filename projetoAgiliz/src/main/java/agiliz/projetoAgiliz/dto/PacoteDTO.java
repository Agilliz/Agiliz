package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PacoteDTO(
        int idTipo,
        int idStatus,
        @NotNull UUID fkDestinatario,
        UUID fkFuncionario
) {
    @Override
    public String toString() {
        return "PacoteDTO{" +
                "tipo=" + idTipo +
                ", status=" + idStatus +
                ", fkDestinatario=" + fkDestinatario +
                ", fkFuncionario=" + fkFuncionario +
                '}';
    }
}
