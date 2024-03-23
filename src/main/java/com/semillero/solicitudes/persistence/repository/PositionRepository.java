package com.semillero.solicitudes.persistence.repository;

import com.semillero.solicitudes.persistence.entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity, Integer> {
}
