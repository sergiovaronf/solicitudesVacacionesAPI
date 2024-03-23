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
public class PositionResponse implements Serializable {
    private Integer idCargo;
    private String cargo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private String activo;
}
