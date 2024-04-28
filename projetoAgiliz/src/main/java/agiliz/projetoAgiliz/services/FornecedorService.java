package agiliz.projetoAgiliz.services;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.models.Fornecedor;

@Service
public class FornecedorService {
    
    public static Page<Fornecedor> removeIdFornecedor(Page<Fornecedor> fornecedorList){
        fornecedorList.stream().forEach(f -> {
                f.getUnidades().forEach(u -> {
                    u.setFornecedor(null);
                });
            });
            
            return fornecedorList;
    }
}
