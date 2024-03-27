package agiliz.projetoAgiliz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import agiliz.projetoAgiliz.models.Destinatario;

public interface IDestinatarioRepository extends JpaRepository<Destinatario, UUID>{
    
}
