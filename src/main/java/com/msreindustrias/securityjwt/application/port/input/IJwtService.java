package com.msreindustrias.securityjwt.application.port.input;

import com.msreindustrias.securityjwt.application.dto.in.LoginRequestDto;
import com.msreindustrias.securityjwt.application.dto.out.JWTAuthResponseDto;


public interface IJwtService {
    JWTAuthResponseDto autenticacionUsuario(LoginRequestDto loginRequestDto);
}
