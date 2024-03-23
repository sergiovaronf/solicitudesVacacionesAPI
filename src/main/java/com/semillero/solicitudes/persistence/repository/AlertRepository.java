package com.semillero.solicitudes.persistence.repository;

import com.semillero.solicitudes.persistence.entities.AlertEntity;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepository extends CrudRepository<AlertEntity, Integer> {
}
