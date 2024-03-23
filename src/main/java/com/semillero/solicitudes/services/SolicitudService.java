package com.semillero.solicitudes.services;

import com.semillero.solicitudes.models.request.SolicitudRequest;
import com.semillero.solicitudes.models.responses.*;
import com.semillero.solicitudes.persistence.entities.UserEntity;
import com.semillero.solicitudes.persistence.repository.EmployeeRepository;
import com.semillero.solicitudes.persistence.repository.SolicitudRepository;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.persistence.repository.UserRepository;
import com.semillero.solicitudes.persistence.repository.UserRoleRepository;
import com.semillero.solicitudes.services.interfaces.ISolicitud;
import com.semillero.solicitudes.util.enums.SortType;
import com.semillero.solicitudes.util.enums.StateType;
import com.semillero.solicitudes.util.exeptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class SolicitudService implements ISolicitud {

    private final UserRepository userRepository;
    private final SolicitudRepository solicitudRepository;

    @Override
    public SolicitudResponse create(SolicitudRequest request) {
        var user = userRepository.findById(request.getIdUsuario()).orElseThrow(()-> new IdNotFoundException("usuario"));
        validRequest(user, request);
        LocalDate fechaActual = LocalDate.now();
        var solicitudToPersist = SolicitudEntity.builder()
                .usuario(user)
                .diasSolicita(request.getDiasSolicita())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .fechaRetorna(request.getFechaRetorna())
                .estado(request.getEstado().toString())
                .observaciones(request.getObservaciones())
                .fechaCreacion(fechaActual)
                .build();
        var solicitudPersist = this.solicitudRepository.save(solicitudToPersist);
        return this.entityToResponse(solicitudPersist);
    }

    @Override
    public SolicitudResponse read(Integer id) {
        var solicitud = this.solicitudRepository.findById(id).orElseThrow(()-> new IdNotFoundException("solicitudes"));
        return entityToResponse(solicitud);
    }

    @Override
    public SolicitudResponse update(SolicitudRequest request, Integer id) {
        var solicitud = this.solicitudRepository.findById(id).orElseThrow(()-> new IdNotFoundException("solicitudes"));
        var user = this.userRepository.findById(request.getIdUsuario()).orElseThrow(()-> new IdNotFoundException("usuario"));
        solicitud.setUsuario(user);
        solicitud.setDiasSolicita(request.getDiasSolicita());
        solicitud.setFechaInicio(request.getFechaInicio());
        solicitud.setFechaFin(request.getFechaFin());
        solicitud.setFechaRetorna(request.getFechaRetorna());
        solicitud.setEstado(request.getEstado().toString());
        solicitud.setObservaciones(request.getObservaciones());
        var solicitudUpdate = this.solicitudRepository.save(solicitud);
        return entityToResponse(solicitudUpdate);
    }
    @Override
    public void delete(Integer id) {
        var solicitud = this.solicitudRepository.findById(id).orElseThrow();
        this.solicitudRepository.delete(solicitud);
    }


    @Override
    public List<SolicitudResponse> getAll() {
        List<SolicitudEntity> solicitud = this.solicitudRepository.findAll();
        return solicitud.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitudResponse> getAllEmployee(Integer id) {
        List<SolicitudEntity> solicitud = this.solicitudRepository.findByEmployeeId(id);
        return solicitud.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SolicitudResponse> getAllPage(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = PageRequest.of(page, size);
        switch (sortType){
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return solicitudRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public Page<SolicitudResponse> getAllPageEmployee(Integer page, Integer size, Integer id) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return solicitudRepository.findPageByEmployeeId(id, pageRequest).map(this::entityToResponse);
    }

    //Reglas de la solicitud

    private void validRequest(UserEntity user, SolicitudRequest request){
        //Validar si cumple el año de ingreso
        LocalDate date1 =  user.getEmpleado().getFechaIngreso();
        LocalDate date2 =  LocalDate.now();
        long year = ChronoUnit.YEARS.between(date1, date2);
        if(year < 1){
            throw new IllegalArgumentException("El empleado aun no ha cumplido el año en la empresa");
        }

        //Validar si tiene una solicitud en proceso
        Integer daysRequest = solicitudRepository.countByEmployeeIdAndEstado(StateType.ENPROCESO.toString(), user.getEmpleado().getIdEmpleado());
        if(daysRequest > 1){
            throw new IllegalArgumentException("El empleado tiene una solicitud ENPROCESO");
        }

        //Validar si tiene dias disponibles basado en los 15 dias
        Integer daysAprovee = solicitudRepository.countByDays(StateType.APROBADA.toString(), user.getEmpleado().getIdEmpleado());
        daysAprovee = daysAprovee == null ? 0 : daysAprovee;
        long availableDays = (15 * year) - daysAprovee;
        if(request.getDiasSolicita() > availableDays){
            throw new IllegalArgumentException("El empleado no tiene dias disponibles");
        }
    }

    private SolicitudResponse entityToResponse(SolicitudEntity entity){
        var response = new SolicitudResponse();
        BeanUtils.copyProperties(entity, response);

        var userResponse = new UserResponse();
        BeanUtils.copyProperties(entity.getUsuario(), userResponse);

        var employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(entity.getUsuario().getEmpleado(), employeeResponse);

        var positionResponse = new PositionResponse();
        BeanUtils.copyProperties(entity.getUsuario().getEmpleado().getCargo(), positionResponse);
        employeeResponse.setCargo(positionResponse);
        userResponse.setEmpleado(employeeResponse);

        var rolUserResponse = new UserRoleResponse();
        BeanUtils.copyProperties(entity.getUsuario().getRol(), rolUserResponse);
        userResponse.setRol(rolUserResponse);

        response.setUsuario(userResponse);
        return response;
    }


}
