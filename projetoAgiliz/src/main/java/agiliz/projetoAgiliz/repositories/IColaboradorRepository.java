package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.dto.LoginDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IColaboradorRepository extends JpaRepository<Colaborador, UUID> {

    @Query("SELECT new Colaborador(f.emailColaborador, f.senhaColaborador) FROM Colaborador f WHERE f.emailColaborador = :email")
    Optional<LoginDTO> findByEmailColaborador(@Param("email") String email);
    
}
