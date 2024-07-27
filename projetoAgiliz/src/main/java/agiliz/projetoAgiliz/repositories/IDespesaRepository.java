package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDespesaRepository extends JpaRepository<Despesa, UUID> {

}
