package com.msreindustrias.securityjwt.application.dto.in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DatosPersonalesRequestDto {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String correo;
    private String ciudad;
    private String nombreUsuario;
    private String rol;
}
