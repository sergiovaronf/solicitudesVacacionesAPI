package com.semillero.solicitudes.persistence.repository;

import com.semillero.solicitudes.models.responses.UserResponse;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM usuario u WHERE u.empleado.idEmpleado = :id")
    UserEntity findByEmployeeId(Integer id);

}
