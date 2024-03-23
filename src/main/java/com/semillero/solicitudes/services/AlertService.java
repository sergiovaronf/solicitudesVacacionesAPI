package com.semillero.solicitudes.services;

import com.semillero.solicitudes.models.request.AlertRequest;
import com.semillero.solicitudes.models.responses.AlertResponse;
import com.semillero.solicitudes.persistence.entities.AlertEntity;
import com.semillero.solicitudes.persistence.repository.AlertRepository;
import com.semillero.solicitudes.services.interfaces.IAlert;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class AlertService implements IAlert {

    private final AlertRepository alertRepository;
    @Override
    public AlertResponse create(AlertRequest request) {
        LocalDate fechaActual = LocalDate.now();
        var alertToPersist = AlertEntity.builder()
                .asunto(request.getAsunto())
                .destinatario(request.getDestinatario())
                .contenidoAlerta(request.getContenidoAlerta())
                .estadoAlerta(request.getEstadoAlerta())
                .fechaCreacion(fechaActual)
                .build();
        var alertPersist = this.alertRepository.save(alertToPersist);
        return entityToResponse(alertPersist);
    }

    @Override
    public AlertResponse read(Integer id) {
        var alert = this.alertRepository.findById(id).orElseThrow();
        return entityToResponse(alert);
    }

    @Override
    public AlertResponse update(AlertRequest request, Integer id) {
        var alert = this.alertRepository.findById(id).orElseThrow();
        alert.setAsunto(request.getAsunto());
        alert.setDestinatario(request.getDestinatario());
        alert.setContenidoAlerta(request.getContenidoAlerta());
        alert.setEstadoAlerta(request.getEstadoAlerta());
        var alertUpdate = this.alertRepository.save(alert);
        return entityToResponse(alertUpdate);
    }

    @Override
    public void delete(Integer id) {
        var alert = this.alertRepository.findById(id).orElseThrow();
        this.alertRepository.delete(alert);
    }

    private AlertResponse entityToResponse(AlertEntity entity){
        var response = new AlertResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
