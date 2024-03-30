package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IPagamentoRepository extends JpaRepository<Pagamento, UUID> {
    List<Pagamento> findByColaborador(Colaborador colaborador);
}
