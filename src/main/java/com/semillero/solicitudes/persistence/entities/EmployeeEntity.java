package com.semillero.solicitudes.persistence.entities;

import com.semillero.solicitudes.util.enums.StateUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Table(name = "empleado")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nm_id_empleado")
    private Integer idEmpleado;

    @Column(name = "nm_documento")
    private Integer documento;

    @Column(name = "ds_tipo_documento")
    private String tipoDocumento;

    @Column(name = "ds_nombre")
    private String nombre;

    @Column(name = "ds_apellido")
    private String apellido;

    @Column(name = "ds_telefono")
    private String telefono;

    @Column(name = "ds_direccion")
    private String direccion;

    @Column(name = "fe_fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fe_fecha_retiro")
    private LocalDate fechaRetiro;

    @Column(name = "ds_tipo_contrato")
    private String tipoContrato;

    @Column(name = "ds_estado_empleado")
    private String estadoEmpleado;

    @Column(name = "nm_supervisor_inmediato")
    private Integer supervisorInmediato;

    @ManyToOne
    @JoinColumn(name = "nm_cargo")
    private PositionEntity cargo;
}
