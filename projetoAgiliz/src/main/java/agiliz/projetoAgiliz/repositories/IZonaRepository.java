package agiliz.projetoAgiliz.repositories;

import agiliz.projetoAgiliz.models.ZonaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IZonaRepository extends CrudRepository<ZonaModel, UUID> {

}
