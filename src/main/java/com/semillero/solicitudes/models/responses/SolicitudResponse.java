package com.semillero.solicitudes.models.responses;

import com.semillero.solicitudes.util.enums.StateType;
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
public class SolicitudResponse implements Serializable {

    private Integer idSolicitud;

    private UserResponse usuario;

    private Integer diasSolicita;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private LocalDate fechaRetorna;

    private String estado;

    private String observaciones;

    private LocalDate fechaCreacion;
}
