package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.dto.colaborador.LoginDTO;
import agiliz.projetoAgiliz.dto.colaborador.MatrizColaboradorDTO;
import agiliz.projetoAgiliz.dto.dashEntregas.TotalAusenteECanceladasDTO;
import agiliz.projetoAgiliz.dto.dashEntregas.TotalEntregaDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IColaboradorRepository extends JpaRepository<Colaborador, UUID> {

        @Query("SELECT new Colaborador(f.emailColaborador, f.senhaColaborador) FROM Colaborador f WHERE f.emailColaborador = :email")
        Optional<LoginDTO> findByEmailColaborador(@Param("email") String email);

        @Query("SELECT new agiliz.projetoAgiliz.dto.colaborador.MatrizColaboradorDTO(e.valor, f.cpf) FROM EmissaoPagamento e LEFT JOIN e.colaborador f")
        List<MatrizColaboradorDTO> listarMatriz();

        @Query("""
                        SELECT new agiliz.projetoAgiliz.dto.dashEntregas.TotalEntregaDTO(
                        COUNT(CASE WHEN p.status = 2 THEN p.idPacote ELSE NULL END) AS entregues,
                        COUNT(p.idPacote) AS total
                        ) FROM Pacote p
                        """)
        TotalEntregaDTO listarTotalEntreguesETotalPacotes();

        @Query("SELECT new agiliz.projetoAgiliz.dto.dashEntregas.TotalAusenteECanceladasDTO(" +
           "SUM(CASE WHEN p.status = 3 THEN 1 ELSE 0 END), " +
           "SUM(CASE WHEN p.status = 5 THEN 1 ELSE 0 END)) " +
           "FROM Pacote p")
        TotalAusenteECanceladasDTO lisTotalAusenteECanceladas();

        @Query("""
                        SELECT new agiliz.projetoAgiliz.dto.dashEntregas.TotalEntregaDTO(
                        COUNT(CASE WHEN p.status = 2 THEN p.idPacote ELSE NULL END) AS entregues,
                        COUNT(p.idPacote) AS total
                        ) FROM Pacote p
                        """)
        TotalEntregaDTO listarTotalEmRotaETotalPacotes();

        @Query("SELECT p.colaborador.nomeColaborador " +
                        "FROM Pacote p " +
                        "WHERE p.status = 2 " +
                        "GROUP BY p.colaborador.nomeColaborador " +
                        "ORDER BY COUNT(p.idPacote) DESC")
        List<String> findColaboradorComMaisPacotes();

        @Query("SELECT p.colaborador.nomeColaborador " +
                        "FROM Pacote p " +
                        "WHERE p.status = 2 " +
                        "GROUP BY p.colaborador.nomeColaborador " +
                        "ORDER BY COUNT(p.idPacote) ASC")
        List<String> findColaboradorComMenosPacotes();
}
