package com.semillero.solicitudes.models.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRoleRequest implements Serializable {
    @NotNull(message = "El rol es necesario")
    @Size(max = 30, message = "El tamaño es de maximo de 30 caracteres")
    private String rol;
}
