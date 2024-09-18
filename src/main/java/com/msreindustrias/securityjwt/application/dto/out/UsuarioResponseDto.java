package com.msreindustrias.securityjwt.application.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UsuarioResponseDto {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String correo;
    private String ciudad;
    private String usuario;
    private String rol;
}
