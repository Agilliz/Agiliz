package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.dto.ColaboradorResponse;
import agiliz.projetoAgiliz.models.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IColaboradorRepository extends JpaRepository<Colaborador, UUID> {

    @Query("SELECT c FROM Colaborador c")
    List<ColaboradorResponse> findAllColaboradorResponse();
}
