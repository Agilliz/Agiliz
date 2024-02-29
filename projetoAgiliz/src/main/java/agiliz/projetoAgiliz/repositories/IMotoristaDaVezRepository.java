package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.MotoristaDaVezModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMotoristaDaVezRepository extends JpaRepository<MotoristaDaVezModel, UUID> {
}
