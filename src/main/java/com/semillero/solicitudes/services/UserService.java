package com.semillero.solicitudes.services;

import com.semillero.solicitudes.models.request.UserRequest;
import com.semillero.solicitudes.models.responses.EmployeeResponse;
import com.semillero.solicitudes.models.responses.PositionResponse;
import com.semillero.solicitudes.models.responses.UserRoleResponse;
import com.semillero.solicitudes.models.responses.UserResponse;
import com.semillero.solicitudes.persistence.entities.EmployeeEntity;
import com.semillero.solicitudes.persistence.entities.UserEntity;
import com.semillero.solicitudes.persistence.repository.EmployeeRepository;
import com.semillero.solicitudes.persistence.repository.UserRoleRepository;
import com.semillero.solicitudes.persistence.repository.UserRepository;
import com.semillero.solicitudes.services.interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUser {

    private final EmployeeRepository employeeRepository;
    private final UserRoleRepository rolUsuarioRepository;
    private final UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest request) {
        var employee = employeeRepository.findById(request.getIdEmpleado()).orElseThrow();
        var role = rolUsuarioRepository.findById(request.getIdRol()).orElseThrow();
        var userToPersist = UserEntity.builder()
                .empleado(employee)
                .usuario(request.getUsuario())
                .activo(request.getActivo().toString())
                .contraseña(request.getContraseña())
                .rol(role)
                .build();
        var userPersist = this.userRepository.save(userToPersist);
        return this.entityToResponse(userPersist);
    }

    @Override
    public UserResponse read(Integer id) {
        var user = this.userRepository.findById(id).orElseThrow();
        return entityToResponse(user);
    }

    @Override
    public UserResponse update(UserRequest request, Integer id) {
        var user = this.userRepository.findById(id).orElseThrow();
        var employee = this.employeeRepository.findById(request.getIdEmpleado()).orElseThrow();
        var role = rolUsuarioRepository.findById(request.getIdRol()).orElseThrow();
        user.setEmpleado(employee);
        user.setUsuario(request.getUsuario());
        user.setActivo(request.getActivo().toString());
        user.setRol(role);
        var userUpdate = this.userRepository.save(user);
        return entityToResponse(userUpdate);
    }

    @Override
    public void delete(Integer id) {
        var user = this.userRepository.findById(id).orElseThrow();
        this.userRepository.delete(user);
    }

    private UserResponse entityToResponse(UserEntity entity){
        var response = new UserResponse();
        BeanUtils.copyProperties(entity, response);

        var employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(entity.getEmpleado(), employeeResponse);

        var positionResponse = new PositionResponse();
        BeanUtils.copyProperties(entity.getEmpleado().getCargo(), positionResponse);
        employeeResponse.setCargo(positionResponse);
        response.setEmpleado(employeeResponse);

        var rolUserResponse = new UserRoleResponse();
        BeanUtils.copyProperties(entity.getRol(), rolUserResponse);
        response.setRol(rolUserResponse);

        return response;
    }

    @Override
    public List<UserResponse> getAll() {
        List<UserEntity> users = this.userRepository.findAll();
        return users.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }
}
