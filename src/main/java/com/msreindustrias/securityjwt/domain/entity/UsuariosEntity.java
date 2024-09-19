package com.msreindustrias.securityjwt.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "usuarios")
public class UsuariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuarios;

    @NotNull
    @Column(name = "id_persona")
    private long idPersona;

    @NotNull
    @Column(name = "id_rol")
    private long idRol;

    @NotNull
    @Column(name = "usuario")
    @Size(max=50)
    private String usuario;

    @NotNull
    @Column(name = "correo")
    @Size(max=100)
    private String correo;

    @NotNull
    @Column(name = "password")
    @Size(max=65)
    private String password;

    @NotNull
    @Column(name = "estado")
    @Size(max=20)
    private String estado;

    @NotNull
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id_rol"))
    private Set<RolEntity> roles = new HashSet<>();
}
