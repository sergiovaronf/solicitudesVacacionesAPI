package com.semillero.solicitudes.persistence.repository;

import com.semillero.solicitudes.persistence.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}
