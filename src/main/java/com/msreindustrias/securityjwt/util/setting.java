package com.msreindustrias.securityjwt.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class setting {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;
}
