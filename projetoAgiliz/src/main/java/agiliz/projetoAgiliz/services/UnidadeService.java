package agiliz.projetoAgiliz.services;


import org.springframework.data.domain.Page;

import agiliz.projetoAgiliz.models.Unidade;

public class UnidadeService {
    public static Page<Unidade> removeFornecedorListUnidade(Page<Unidade> unidades){
        unidades.stream().forEach(u -> {
            u.setFornecedor(null);
        });
        return unidades;
    }
}
