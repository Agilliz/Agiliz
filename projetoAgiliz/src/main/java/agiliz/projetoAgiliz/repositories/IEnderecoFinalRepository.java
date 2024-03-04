package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.EnderecoFinal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEnderecoFinalRepository extends JpaRepository<EnderecoFinal, UUID> {
}
