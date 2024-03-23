package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity(name = "usuario")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_usuario")
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "nm_id_empleado")
    private EmployeeEntity empleado;

    @Column(name = "ds_usaurio")
    private String usuario;

    @Column(name = "ds_activo")
    private String activo;

    @Column(name = "ds_contraseña")
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "nm_rol", referencedColumnName = "nm_id_rol")
    private UserRoleEntity rol;
}
