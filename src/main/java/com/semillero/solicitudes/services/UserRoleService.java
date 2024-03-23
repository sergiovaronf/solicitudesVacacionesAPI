package com.semillero.solicitudes.services;

import com.semillero.solicitudes.models.request.UserRoleRequest;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.models.responses.UserRoleResponse;
import com.semillero.solicitudes.persistence.entities.PositionEntity;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.entities.UserRoleEntity;
import com.semillero.solicitudes.persistence.repository.PositionRepository;
import com.semillero.solicitudes.persistence.repository.UserRoleRepository;
import com.semillero.solicitudes.services.interfaces.IUserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class UserRoleService implements IUserRole {

    private final UserRoleRepository userRoleRepository;
    @Override
    public UserRoleResponse create(UserRoleRequest request) {
        LocalDate fechaActual = LocalDate.now();
        var userRoleToPersist = UserRoleEntity.builder()
                .rol(request.getRol())
                .fechaCreacion(fechaActual)
                .build();
        var userRolePersist = this.userRoleRepository.save(userRoleToPersist);
        log.info("Rol {}", userRolePersist.getIdRol());
        return entityToResponse(userRolePersist);
    }

    @Override
    public UserRoleResponse read(Integer id) {
        var userRole = this.userRoleRepository.findById(id).orElseThrow();
        return entityToResponse(userRole);
    }

    @Override
    public UserRoleResponse update(UserRoleRequest request, Integer id) {
        var userRole = this.userRoleRepository.findById(id).orElseThrow();
        userRole.setRol(request.getRol());
        var userRoleUpdate = this.userRoleRepository.save(userRole);
        return entityToResponse(userRoleUpdate);
    }

    @Override
    public void delete(Integer id) {
        var userRole = this.userRoleRepository.findById(id).orElseThrow();
        this.userRoleRepository.delete(userRole);
    }

    @Override
    public List<UserRoleResponse> getAll() {
        List<UserRoleEntity> userRole = this.userRoleRepository.findAll();
        return userRole.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    private UserRoleResponse entityToResponse(UserRoleEntity entity){
        var response = new UserRoleResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
