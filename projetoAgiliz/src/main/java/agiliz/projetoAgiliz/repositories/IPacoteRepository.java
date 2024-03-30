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
    List<Pacote> findByColaboradorAndPagamentoFeito(Colaborador colaborador, boolean pagamentoFeito);
}
