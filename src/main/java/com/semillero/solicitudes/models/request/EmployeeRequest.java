package com.semillero.solicitudes.models.request;

import com.semillero.solicitudes.util.enums.StateUser;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeRequest implements Serializable {
    @NotNull(message = "El documento es necesario")
    @Positive(message = "Los documento deben ser un valor positivo")
    private Long documento;

    @NotNull(message = "El tipoDocumento es necesario")
    private String tipoDocumento;

    @NotNull(message = "El nombre es necesario")
    @Size(max = 30, message = "El tamaño de contraseña es de 30 caracteres")
    private String nombre;

    @NotNull(message = "El apellido es necesario")
    @Size(max = 30, message = "El tamaño de contraseña es de 30 caracteres")
    private String apellido;

    @NotNull(message = "El telefono es necesario")
    @Size(max = 14, message = "El tamaño de contraseña es de 14 caracteres")
    private String telefono;

    @NotNull(message = "El direccion es necesario")
    @Size(max = 60, message = "El tamaño de contraseña es de 60 caracteres")
    private String direccion;

    @NotNull(message = "El fechaIngreso es necesario")
    private LocalDate fechaIngreso;

    private LocalDate fechaRetiro;

    @NotNull(message = "El tipoContrato es necesario")
    @Size(max = 20, message = "El tipoContrato es de 20 caracteres")
    private String tipoContrato;

    @NotNull(message = "El estadoEmpleado es necesario")
    @Enumerated(EnumType.STRING)
    private StateUser estadoEmpleado;

    private Integer supervisorInmediato;

    @NotNull(message = "El idCargo es necesario")
    private Integer idCargo;
}
