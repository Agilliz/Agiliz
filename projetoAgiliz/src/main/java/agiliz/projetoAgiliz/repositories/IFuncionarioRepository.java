package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {
}
