package agiliz.projetoAgiliz.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import agiliz.projetoAgiliz.models.PacoteModel;

@Repository
public interface IPacoteRepository extends CrudRepository<PacoteModel, UUID>{
    
}
