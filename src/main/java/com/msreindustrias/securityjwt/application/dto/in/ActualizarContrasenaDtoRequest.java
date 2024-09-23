package com.msreindustrias.securityjwt.application.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarContrasenaDtoRequest {
    private String correo;
    private String usuario;
    private String contrasenaVieja;
    private String contrasenaNueva;
}
