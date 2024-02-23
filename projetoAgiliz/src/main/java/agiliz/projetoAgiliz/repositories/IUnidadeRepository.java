package agiliz.projetoAgiliz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agiliz.projetoAgiliz.models.UnidadeModel;


@Repository
public interface IUnidadeRepository extends JpaRepository<UnidadeModel, UUID>{
    
}
