package agiliz.projetoAgiliz.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;

public record FornecedorDTO(@NotBlank String fornecedorNome, @NotBlank @CNPJ String matrizCnpj){

}
