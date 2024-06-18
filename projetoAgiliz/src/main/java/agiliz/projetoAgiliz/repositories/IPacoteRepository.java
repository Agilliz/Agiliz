package agiliz.projetoAgiliz.repositories;

import java.util.List;
import java.util.UUID;

import agiliz.projetoAgiliz.dto.PacotePorcentagemDTO;
import agiliz.projetoAgiliz.dto.RankingEntregasDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacoteRepository extends JpaRepository<Pacote, UUID> {

    // @Query("SELECT p FROM Pacote p WHERE p.colaborador = ?1 AND p.status = 3 AND
    // pagamentoFeito = false")
    // List<Pacote> findPackagesForPayment(Colaborador colaborador);
    @Query("SELECT p FROM Pacote p WHERE p.colaborador = ?1 AND p.status = 3")
    List<Pacote> findPackagesForPayment(Colaborador colaborador);

    @Query("SELECT p.zona.valor FROM Pacote p WHERE p.status = 3")
    List<Double> findZonaPrices();

    @Query(
        "SELECT new agiliz.projetoAgiliz.dto.RankingEntregasDTO(" +
        "z.nomeZona, " +
        "ROUND(COUNT(p) * 100.0 / (SELECT COUNT(p2) FROM Pacote p2), 2)) " +
        "FROM Pacote p " +
        "JOIN p.zona z " +
        "GROUP BY z.nomeZona " +
        "ORDER BY COUNT(p) DESC"
    )
    List<RankingEntregasDTO> listarRankingEntregas();

    @Query("SELECT new agiliz.projetoAgiliz.dto.PacotePorcentagemDTO(" +
                  "(SUM(CASE WHEN p.status = 3 THEN 1 ELSE 0 END) * 100.0 / COUNT(p.id)), " +
                  "(SUM(CASE WHEN p.status = 2 THEN 1 ELSE 0 END) * 100.0 / COUNT(p.id)), " +
                  "(SUM(CASE WHEN p.status = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(p.id)) " +
                  ") " +
                  "FROM Pacote p")
    List<PacotePorcentagemDTO>listarPorcentagem();
}
