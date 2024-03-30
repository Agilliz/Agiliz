package agiliz.projetoAgiliz.repositories;

import java.util.List;
import java.util.UUID;

import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacoteRepository extends JpaRepository<Pacote, UUID>{

    @Query("SELECT p FROM Pacote p WHERE p.colaborador = ?1 AND p.status = 3 AND pagamentoFeito = false")
    List<Pacote> findPackagesForPayment(Colaborador colaborador);
}
