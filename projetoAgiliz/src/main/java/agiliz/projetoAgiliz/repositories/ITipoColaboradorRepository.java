package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.dto.TipoColaboradorResponse;
import agiliz.projetoAgiliz.models.TipoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ITipoColaboradorRepository extends JpaRepository<TipoColaborador, UUID> {

    @Query("SELECT t FROM TipoColaborador t")
    List<TipoColaboradorResponse> findAllTipoColaboradorResponse();
}
