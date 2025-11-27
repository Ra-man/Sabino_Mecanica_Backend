package com.sabinomecanica.backend.repositories;

import com.sabinomecanica.backend.models.ServicoPeca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicoPecaRepository extends JpaRepository<ServicoPeca, UUID> {

}
