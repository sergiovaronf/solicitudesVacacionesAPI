package com.semillero.solicitudes.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse implements Serializable {
    private Integer idUsuario;
    private String usuario;
    private String activo;
    private EmployeeResponse empleado;
    private UserRoleResponse rol;
}
