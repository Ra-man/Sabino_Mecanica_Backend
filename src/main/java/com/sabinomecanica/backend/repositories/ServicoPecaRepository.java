package com.sabinomecanica.backend.repositories;

import com.sabinomecanica.backend.models.ServicoPeca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoPecaRepository extends JpaRepository<ServicoPeca, UUID> {
}
