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
@Table(name = "rol_usuario")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_rol")
    private Integer idRol;

    @Column(name = "ds_rol")
    private String rol;

    @Column(name = "fe_fecha_creacion")
    private LocalDate fechaCreacion;
}
