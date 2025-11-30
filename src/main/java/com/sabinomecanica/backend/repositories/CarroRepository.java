package com.sabinomecanica.backend.repositories;

import com.sabinomecanica.backend.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarroRepository extends JpaRepository<Carro, UUID> {
}
