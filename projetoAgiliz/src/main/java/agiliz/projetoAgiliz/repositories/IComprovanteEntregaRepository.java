package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.ComprovanteEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IComprovanteEntregaRepository extends JpaRepository<ComprovanteEntrega, UUID> {
}
