package com.msreindustrias.securityjwt.application.dto.in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecuperarPasswordRequestDto {
    private String correo;
    private String usuario;
}
