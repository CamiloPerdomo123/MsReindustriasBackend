package com.msreindustrias.securityjwt.application.dto.in;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UsuarioRequestDto {
    private long idUsuarios;
    private long idPersona;
    private String usuario;
    private String password;
    private String estado;
    private String rol;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
