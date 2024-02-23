package agiliz.projetoAgiliz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agiliz.projetoAgiliz.models.FornecedorModel;

@Repository
public interface IFornecedorRepository extends JpaRepository<FornecedorModel, UUID>{
    
}
