package agiliz.projetoAgiliz.repositories;

import java.util.List;
import java.util.UUID;

import agiliz.projetoAgiliz.dto.ColetasPorTempo;
import agiliz.projetoAgiliz.dto.ZonaRanking;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.models.Pacote;
import agiliz.projetoAgiliz.models.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacoteRepository extends JpaRepository<Pacote, UUID>{

//    @Query("SELECT p FROM Pacote p WHERE p.colaborador = ?1 AND p.status = 3 AND pagamentoFeito = false")
//    List<Pacote> findPackagesForPayment(Colaborador colaborador);
    @Query("SELECT p FROM Pacote p WHERE p.colaborador = ?1 AND p.status = 2 AND p.tipo = 1")
    List<Pacote> findPackagesForPayment(Colaborador colaborador);

    @Query("SELECT p.zona.valor FROM Pacote p WHERE p.status = 2 AND p.tipo = 1")
    List<Double> findZonaPrices();

    @Query("""
        SELECT
            new agiliz.projetoAgiliz.dto.ZonaRanking(p.zona.nomeZona, (CAST((COUNT(p) / ?1) AS DOUBLE)) * 100) 
        FROM Pacote p
        WHERE p.tipo = 2 
        GROUP BY p.zona        
    """)
    List<ZonaRanking> findZonasRanking(int quantidadePacotes);

    @Query("SELECT new Pacote(p.status) FROM Pacote p WHERE p.tipo = 2")
    List<Pacote> findAllPacoteStatusOnly();

    @Query("""
        SELECT
            new agiliz.projetoAgiliz.dto.ColetasPorTempo(COUNT(p), p.dataColeta)
        FROM Pacote p
        WHERE p.dataColeta is not null
        GROUP BY p.dataColeta
    """)
    List<ColetasPorTempo> findColetasPorTempo();
    @Query("SELECT p.unidade.nomeUnidade FROM Pacote p ORDER BY p.zona.valor ASC LIMIT 1")
    String findClienteMaiorColeta();
    @Query("SELECT p.unidade.nomeUnidade FROM Pacote p ORDER BY p.zona.valor DESC LIMIT 1")
    String findClienteMenorColeta();
    @Query("SELECT p.unidade FROM Pacote p WHERE p.status = 2 AND p.tipo = 2 GROUP BY p.unidade")
    List<Unidade> countColetasRealizadas();
    @Query("SELECT p.unidade FROM Pacote p WHERE p.status = 3 AND p.tipo = 2 GROUP BY p.unidade")
    List<Unidade> countColetasCanceladas();
}
