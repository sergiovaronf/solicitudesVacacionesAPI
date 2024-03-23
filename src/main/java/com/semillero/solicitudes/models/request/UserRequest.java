package com.semillero.solicitudes.models.request;

import com.semillero.solicitudes.util.enums.StateUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest implements Serializable {
    @NotNull(message = "El idEmpleado es necesario")
    private Integer idEmpleado;

    @NotNull(message = "El usuario es necesario")
    @Size(max = 10, message = "El tamaño de usuario es de 10 caracteres")
    private String usuario;

    @NotNull(message = "El activo es necesario")
    @Enumerated(EnumType.STRING)
    private StateUser activo;

    @NotNull(message = "La contraseña es necesaria")
    @Size(max = 10, message = "El tamaño de contraseña es de 10 caracteres")
    private String contraseña;

    private Integer idRol;
}
