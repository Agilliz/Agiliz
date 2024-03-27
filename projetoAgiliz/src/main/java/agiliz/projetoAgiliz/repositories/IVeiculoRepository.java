package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IVeiculoRepository extends JpaRepository<Veiculo, UUID> {
}
