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
public class AlertRequest implements Serializable {
    @NotNull(message = "El asunto es necesario")
    @Size(max = 20, message = "observaciones es de maximo de 20 caracteres")
    private String asunto;

    @NotNull(message = "El destinatario es necesario")
    @Size(max = 40, message = "observaciones es de maximo de 40 caracteres")
    private String destinatario;

    @NotNull(message = "El contenidoAlerta es necesario")
    @Size(max = 40, message = "observaciones es de maximo de 40 caracteres")
    private String contenidoAlerta;

    @Size(max = 10, message = "observaciones es de maximo de 10 caracteres")
    @NotNull(message = "El estadoAlerta es necesario")
    private String estadoAlerta;
}
