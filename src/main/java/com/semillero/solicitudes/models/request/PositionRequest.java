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
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PositionRequest implements Serializable {

    @NotNull(message = "El cargo es necesario")
    @Size(max = 50, message = "El tamaño de cargo de 50 caracteres")
    private String cargo;

    @NotNull(message = "El descripcion es necesario")
    @Size(max = 100, message = "El tamaño de descripcion es de 100 caracteres")
    private String descripcion;

    @NotNull(message = "El activo es necesario")
    @Enumerated(EnumType.STRING)
    private StateUser activo;
}
