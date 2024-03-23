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
public class AlertResponse implements Serializable {
    private Integer idAlerta;
    private String asunto;
    private String destinatario;
    private String contenidoAlerta;
    private String estadoAlerta;
    private LocalDate fechaCreacion;
}
