package com.msreindustrias.securityjwt.application.port.input;

import org.springframework.security.core.Authentication;

public interface IJwtTokenProviderService {
    String generarToken(Authentication authentication);
}
