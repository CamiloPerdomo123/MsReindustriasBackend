package com.msreindustrias.securityjwt.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "datos_personales")
public class DatosPersonalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Datos_Personales")
    private long idDatosPersonales;

    @NotNull
    @Column(name = "nombre")
    @Size(max=30)
    private String nombre;

    @NotNull
    @Column(name = "apellido")
    @Size(max=30)
    private String apellido;

    @NotNull
    @Column(name = "tipoDocumento")
    @Size(max=30)
    private String tipoDocumento;

    @NotNull
    @Column(name = "numeroDocumento")
    @Size(max=30)
    private String numeroDocumento;

    @NotNull
    @Column(name = "telefono")
    @Size(max=30)
    private String telefono;

    @NotNull
    @Column(name = "ciudad")
    @Size(max=40)
    private String ciudad;

    @NotNull
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
