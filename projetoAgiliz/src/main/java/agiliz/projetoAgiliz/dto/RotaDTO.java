package agiliz.projetoAgiliz.dto;

import agiliz.projetoAgiliz.models.Endereco;
import lombok.Getter;

import java.util.List;

@Getter
public class RotaDTO{
    List<Endereco> entregas;
    Endereco inicio;
    Endereco fim;
}
