package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.Despesa;
import agiliz.projetoAgiliz.models.PrecificacaoZona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPrecificacaoZona extends JpaRepository<PrecificacaoZona, UUID> {
}
