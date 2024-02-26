package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.ZonaModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IZonaRepository extends JpaRepository<ZonaModel, UUID> {
    ZonaModel findByLimiteInferiorGreaterThanEqualAndLimiteSuperiorLessThanEqual();
}
