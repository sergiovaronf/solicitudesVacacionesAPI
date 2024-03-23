package com.semillero.solicitudes.models.request;

import com.semillero.solicitudes.models.validator.constraints.BusinessDay;
import com.semillero.solicitudes.util.enums.StateType;
import jakarta.validation.constraints.*;
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
public class SolicitudRequest implements Serializable {

    @NotNull(message = "El id del usuario es necesario")
    private Integer idUsuario;

    @NotNull(message = "El dia es necesario")
    @Positive(message = "Los dias deben ser un valor positivo")
    @Min(value = 1, message = "Minimo de 1 dia")
    private Integer diasSolicita;

    @NotNull(message = "La fechaInicio es necesaria")
    @BusinessDay(message = "La fechaInicio no es un día hábil en Colombia")
    private LocalDate fechaInicio;

    @NotNull(message = "La fechaFin es necesaria")
    @BusinessDay(message = "La fechaFin no es un día hábil en Colombia")
    private LocalDate fechaFin;

    @NotNull(message = "La fechaRetorna es necesaria")
    @BusinessDay(message = "La fechaRetorna no es un día hábil en Colombia")
    private LocalDate fechaRetorna;

    @NotNull(message = "El estado es necesario")
    @Enumerated(EnumType.STRING)
    private StateType estado;

    @Size(max = 60, message = "observaciones es de maximo de 60 caracteres")
    private String observaciones;
}
