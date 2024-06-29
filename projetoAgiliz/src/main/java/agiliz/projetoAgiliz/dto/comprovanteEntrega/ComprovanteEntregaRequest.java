package agiliz.projetoAgiliz.dto.comprovanteEntrega;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ComprovanteEntregaRequest (
    @NotNull
    UUID fkPacote
)
{}
