package agiliz.projetoAgiliz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agiliz.projetoAgiliz.models.Fornecedor;

@Repository
public interface IFornecedorRepository extends JpaRepository<Fornecedor, UUID>{
    
}
