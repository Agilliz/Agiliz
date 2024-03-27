package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.Vigencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IVigenciaRepository extends JpaRepository<Vigencia, UUID> {
}
