package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.dto.LoginDTO;
import agiliz.projetoAgiliz.dto.MaiorEMenorEntregaDTO;
import agiliz.projetoAgiliz.dto.MatrizColaboradorDTO;
import agiliz.projetoAgiliz.dto.TotalEntregaDTO;
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

    @Query("SELECT new agiliz.projetoAgiliz.dto.MatrizColaboradorDTO(e.valor, f.CPF) FROM EmissaoPagamento e LEFT JOIN e.colaborador f")
    List<MatrizColaboradorDTO> listarMatriz();

    @Query("""
            SELECT new agiliz.projetoAgiliz.dto.TotalEntregaDTO(
            COUNT(CASE WHEN p.status = 3 THEN p.idPacote ELSE NULL END) AS entregues,
            COUNT(p.idPacote) AS total
            ) FROM Pacote p
            """)
    TotalEntregaDTO listarTotalEntreguesETotalPacotes();

    @Query("""
        SELECT new agiliz.projetoAgiliz.dto.TotalEntregaDTO(
        COUNT(CASE WHEN p.status = 2 THEN p.idPacote ELSE NULL END) AS entregues,
        COUNT(p.idPacote) AS total
        ) FROM Pacote p
        """)
    TotalEntregaDTO listarTotalEmRotaETotalPacotes();

    @Query("SELECT " +
            "new agiliz.projetoAgiliz.dto.MaiorEMenorEntregaDTO(" +
            "(SELECT p.colaborador.nomeColaborador " +
            "FROM Pacote p " +
            "WHERE p.status = 3 " +
            "GROUP BY p.colaborador.nomeColaborador " +
            "ORDER BY COUNT(p.idPacote) DESC " +
            "LIMIT 1), " +
            "(SELECT p.colaborador.nomeColaborador " +
            "FROM Pacote p " +
            "WHERE p.status = 3 " +
            "GROUP BY p.colaborador.nomeColaborador " +
            "ORDER BY COUNT(p.idPacote) ASC " +
            "LIMIT 1) " +
            ")" +
            "FROM Pacote p")
    Optional<MaiorEMenorEntregaDTO> listarColaboradoresComMaiorEMenorEntrega();

}
