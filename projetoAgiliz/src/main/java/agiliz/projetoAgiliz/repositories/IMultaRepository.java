package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.MultaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMultaRepository extends JpaRepository<MultaModel, UUID> {
}
