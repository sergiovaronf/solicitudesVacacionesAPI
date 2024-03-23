package com.semillero.solicitudes.persistence.entities;

import com.semillero.solicitudes.util.enums.StateType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "solicitud")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitud_vaciones")
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_solicitud")
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "nm_id_usuario")
    private UserEntity usuario;

    @Column(name = "nm_dias_solicita")
    private Integer diasSolicita;

    @Column(name = "fe_fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fe_fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fe_fecha_retorna")
    private LocalDate fechaRetorna;

    @Column(name = "ds_estado")
    private String estado;

    @Column(name = "ds_observaciones")
    private String observaciones;

    @Column(name = "fe_fecha_creacion")
    private LocalDate fechaCreacion;
}
