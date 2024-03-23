package com.semillero.solicitudes.persistence.repository;

import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.util.enums.StateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Integer> {

    @Query("SELECT s FROM solicitud s WHERE s.usuario.empleado.id = :id ORDER BY s.fechaCreacion DESC")
    List<SolicitudEntity> findByEmployeeId(Integer id);

    @Query("SELECT s FROM solicitud s WHERE s.usuario.empleado.id = :id ORDER BY s.fechaCreacion DESC")
    Page<SolicitudEntity> findPageByEmployeeId(Integer id, Pageable pageable);

    @Query("SELECT COUNT(s) FROM solicitud s WHERE s.estado = :stateType AND s.usuario.empleado.id = :id")
    Integer countByEmployeeIdAndEstado(String stateType, Integer id);

    @Query("SELECT SUM(s.diasSolicita) FROM solicitud s WHERE s.estado = :stateType AND s.usuario.empleado.id = :id")
    Integer countByDays(String stateType, Integer id);

}
