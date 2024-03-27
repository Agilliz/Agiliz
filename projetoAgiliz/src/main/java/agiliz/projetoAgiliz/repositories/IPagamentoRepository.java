package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IPagamentoRepository extends JpaRepository<Pagamento, UUID> {
    @Query("SELECT p FROM Pagamento p WHERE p.colaborador.idColaborador = ?1")
    List<Pagamento> findByFkColaborador(UUID fkColaborador);
}
