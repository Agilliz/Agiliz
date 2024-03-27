package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.TipoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITipoColaboradorRepository extends JpaRepository<TipoColaborador, UUID> {
}
