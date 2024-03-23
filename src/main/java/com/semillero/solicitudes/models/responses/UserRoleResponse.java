package com.semillero.solicitudes.models.responses;

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
public class UserRoleResponse implements Serializable  {
    private Integer idRol;
    private String rol;
    private LocalDate fechaCreacion;
}
