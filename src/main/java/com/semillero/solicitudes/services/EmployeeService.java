package com.semillero.solicitudes.services;

import com.semillero.solicitudes.models.request.EmployeeRequest;
import com.semillero.solicitudes.models.responses.EmployeeResponse;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.models.responses.UserResponse;
import com.semillero.solicitudes.persistence.entities.EmployeeEntity;
import com.semillero.solicitudes.persistence.repository.EmployeeRepository;
import com.semillero.solicitudes.persistence.repository.PositionRepository;
import com.semillero.solicitudes.persistence.repository.UserRepository;
import com.semillero.solicitudes.services.interfaces.IEmployee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class EmployeeService implements IEmployee {

    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        var position = positionRepository.findById(request.getIdCargo()).orElseThrow();
        var employeeToPersist = EmployeeEntity.builder()
                .cargo(position)
                .documento(request.getDocumento().intValue())
                .tipoDocumento(request.getTipoDocumento())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .direccion(request.getDireccion())
                .fechaIngreso(request.getFechaIngreso())
                .fechaRetiro(null)
                .tipoContrato(request.getTipoContrato())
                .estadoEmpleado(request.getEstadoEmpleado().toString())
                .supervisorInmediato(request.getSupervisorInmediato())
                .build();
        var createEmployee = employeeRepository.save(employeeToPersist);
        return entityToResponse(createEmployee);
    }

    @Override
    public EmployeeResponse read(Integer id) {
        var employee = employeeRepository.findById(id).orElseThrow();
        return entityToResponse(employee);
    }

    @Override
    public EmployeeResponse update(EmployeeRequest request, Integer id) {
        var employee = this.employeeRepository.findById(id).orElseThrow();
        var position = this.positionRepository.findById(request.getIdCargo()).orElseThrow();
        employee.setDocumento(request.getDocumento().intValue());
        employee.setTipoDocumento(request.getTipoDocumento());
        employee.setNombre(request.getNombre());
        employee.setApellido(request.getApellido());
        employee.setTelefono(request.getTelefono());
        employee.setDireccion(request.getDireccion());
        employee.setFechaIngreso(request.getFechaIngreso());
        employee.setFechaRetiro(request.getFechaRetiro());
        employee.setTipoContrato(request.getTipoContrato());
        employee.setEstadoEmpleado(request.getEstadoEmpleado().toString());
        employee.setSupervisorInmediato(request.getSupervisorInmediato());
        employee.setCargo(position);
        var employeeUpdate = this.employeeRepository.save(employee);
        return entityToResponse(employeeUpdate);
    }

    @Override    public void delete(Integer id) {
        var employee = this.employeeRepository.findById(id).orElseThrow();
        this.employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponse> getAll() {
        List<EmployeeEntity> employees = this.employeeRepository.findAll();
        return employees.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EmployeeResponse> getAllPage(Integer page, Integer size) {
        PageRequest pageRequest = null;
        pageRequest = PageRequest.of(page, size);
        return employeeRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    private EmployeeResponse entityToResponse(EmployeeEntity entity){
        var response = new EmployeeResponse();
        BeanUtils.copyProperties(entity, response);
        response.setDocumento(entity.getDocumento().longValue());

        var positionResponse = new PositionResponse();
        BeanUtils.copyProperties(entity.getCargo(), positionResponse);
        response.setCargo(positionResponse);

        var user = userRepository.findByEmployeeId(entity.getIdEmpleado());
        if(user != null){
            response.setRol(user.getRol().getIdRol());
        }
        return response;
    }
}
