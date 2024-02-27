package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.ZonaModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IZonaRepository extends JpaRepository<ZonaModel, UUID> {

    @Query("SELECT z FROM ZonaModel z WHERE z.limiteInferiorCEP <= ?1 AND z.limiteSuperiorCEP >= ?1")
    ZonaModel findByCep(Integer cep);
}
