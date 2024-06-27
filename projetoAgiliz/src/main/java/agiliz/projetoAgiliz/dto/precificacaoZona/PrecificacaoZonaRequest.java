package agiliz.projetoAgiliz.dto.precificacaoZona;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;


public record PrecificacaoZonaRequest(
        @NotNull UUID fkUnidade,
        @NotNull Double preco,
        @NotNull int tipoZona

) {}
