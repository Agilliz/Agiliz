package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IColaboradorRepository extends JpaRepository<Colaborador, UUID> {
}
