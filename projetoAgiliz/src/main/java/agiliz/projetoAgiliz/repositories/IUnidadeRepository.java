package agiliz.projetoAgiliz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import agiliz.projetoAgiliz.models.Unidade;


@Repository
public interface IUnidadeRepository extends JpaRepository<Unidade, UUID>{

    @Query("SELECT u.nomeUnidade FROM Unidade u ORDER BY u.retornoTotal ASC LIMIT 1")
    String findUnidadeMenorRetorno();

    @Query("SELECT u.nomeUnidade FROM Unidade u ORDER BY u.retornoTotal DESC LIMIT 1")
    String findUnidadeMaiorRetorno();
    
}
