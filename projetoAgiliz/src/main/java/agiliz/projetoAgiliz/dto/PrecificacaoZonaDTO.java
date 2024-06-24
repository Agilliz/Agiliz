package agiliz.projetoAgiliz.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;


public record PrecificacaoZonaDTO(
        @NotNull UUID fkUnidade,
        @NotNull Double preco,
        @NotNull int tipoZona

) {}
