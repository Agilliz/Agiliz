package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.dto.comprovanteEntrega.ComprovanteEntregaResponse;
import agiliz.projetoAgiliz.models.ComprovanteEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IComprovanteEntregaRepository extends JpaRepository<ComprovanteEntrega, UUID> {
    @Query("SELECT c FROM ComprovanteEntrega c")
    List<ComprovanteEntregaResponse> findAllResponse();
}
