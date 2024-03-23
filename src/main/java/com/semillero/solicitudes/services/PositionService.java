package com.semillero.solicitudes.services;

import com.semillero.solicitudes.models.request.PositionRequest;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.models.responses.UserRoleResponse;
import com.semillero.solicitudes.persistence.entities.PositionEntity;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.entities.UserRoleEntity;
import com.semillero.solicitudes.persistence.repository.PositionRepository;
import com.semillero.solicitudes.services.interfaces.IPosition;
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
public class PositionService implements IPosition {

    private final PositionRepository positionRepository;

    @Override
    public PositionResponse create(PositionRequest request) {
        LocalDate fechaActual = LocalDate.now();
        var positionToPersist = PositionEntity.builder()
                .cargo(request.getCargo())
                .descripcion(request.getDescripcion())
                .fechaCreacion(fechaActual)
                .activo(request.getActivo().toString())
                .build();
        var positionPersist = this.positionRepository.save(positionToPersist);
        return entityToResponse(positionPersist);
    }

    @Override
    public PositionResponse read(Integer id) {
        var position = this.positionRepository.findById(id).orElseThrow();
        return entityToResponse(position);
    }

    @Override
    public PositionResponse update(PositionRequest request, Integer id) {
        var position = this.positionRepository.findById(id).orElseThrow();
        position.setCargo(request.getCargo());
        position.setDescripcion(request.getDescripcion());
        position.setActivo(request.getActivo().toString());
        var positionUpdate = this.positionRepository.save(position);
        return entityToResponse(positionUpdate);
    }

    @Override
    public void delete(Integer id) {
        var position = this.positionRepository.findById(id).orElseThrow();
        this.positionRepository.delete(position);
    }

    @Override
    public List<PositionResponse> getAll() {
        List<PositionEntity> position = this.positionRepository.findAll();
        return position.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    private PositionResponse entityToResponse(PositionEntity entity){
        var response = new PositionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


}
