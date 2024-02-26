package agiliz.projetoAgiliz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import agiliz.projetoAgiliz.models.DestinatarioModel;

public interface IDestinatarioRepository extends JpaRepository<DestinatarioModel, UUID>{
    
}
