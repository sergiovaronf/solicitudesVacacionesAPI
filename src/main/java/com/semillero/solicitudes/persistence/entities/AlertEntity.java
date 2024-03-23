package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alerta")
public class AlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_alerta")
    private Integer idAlerta;

    @Column(name = "ds_asunto")
    private String asunto;

    @Column(name = "ds_destinatario")
    private String destinatario;

    @Column(name = "ds_contenido_alerta")
    private String contenidoAlerta;

    @Column(name = "ds_estado_alerta")
    private String estadoAlerta;

    @Column(name = "fe_fecha_creacion")
    private LocalDate fechaCreacion;
}
