package agiliz.projetoAgiliz.dto.pacote;

import java.util.UUID;

// Fazer um para atualização
public record PacoteRequest(
        int tipo,
        int status,
        UUID fkDestinatario,
        UUID fkFuncionario,
        UUID fkUnidade
) {}
