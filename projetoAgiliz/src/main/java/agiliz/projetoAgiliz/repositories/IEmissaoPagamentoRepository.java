package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.EmissaoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface IEmissaoPagamentoRepository extends JpaRepository<EmissaoPagamento, UUID> {
}
