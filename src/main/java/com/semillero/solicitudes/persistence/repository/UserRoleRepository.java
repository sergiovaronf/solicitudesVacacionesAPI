package com.semillero.solicitudes.persistence.repository;

import com.semillero.solicitudes.persistence.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
}
