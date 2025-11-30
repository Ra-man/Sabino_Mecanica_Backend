package com.sabinomecanica.backend.repositories;

import com.sabinomecanica.backend.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<Servico, UUID> {
    boolean existsByCarro_Id(UUID id);
}
