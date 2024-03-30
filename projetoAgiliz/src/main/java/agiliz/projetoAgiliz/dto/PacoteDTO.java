package agiliz.projetoAgiliz.dto;

import java.util.UUID;

// Fazer um para atualização
public record PacoteDTO(
        int tipo,
        int status,
        UUID fkDestinatario,
        UUID fkFuncionario
) {}
