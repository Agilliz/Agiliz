package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.VeiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IVeiculoRepository extends JpaRepository<VeiculoModel, UUID> {
}
