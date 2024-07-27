package agiliz.projetoAgiliz.dto.rota;

import lombok.Getter;

import java.util.List;

@Getter
public class Rota {
    List<Endereco> entregas;
    Endereco inicio;
    Endereco fim;
}
