package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cargos")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_cargo")
    private Integer idCargo;

    @Column(name = "ds_cargo")
    private String cargo;

    @Column(name = "ds_descripcion")
    private String descripcion;

    @Column(name = "fe_fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "ds_activo")
    private String activo;
}
