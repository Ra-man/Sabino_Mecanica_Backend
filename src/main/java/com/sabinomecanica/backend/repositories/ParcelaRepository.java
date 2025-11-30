package com.sabinomecanica.backend.repositories;

import com.sabinomecanica.backend.models.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParcelaRepository extends JpaRepository<Parcela, UUID> {
}
