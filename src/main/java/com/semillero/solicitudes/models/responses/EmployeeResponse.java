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
public class EmployeeResponse implements Serializable {
    private Integer idEmpleado;
    private String tipoDocumento;
    private Long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private LocalDate fechaIngreso;
    private LocalDate fechaRetiro;
    private String tipoContrato;
    private String estadoEmpleado;
    private Integer supervisorInmediato;
    private Integer diasRestantes;
    private PositionResponse cargo;
    private Integer rol;
}
