package com.msreindustrias.securityjwt.application.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTAuthResonseDTO {

	private String tokenDeAcceso;
	private String tipoDeToken = "Bearer";

	public JWTAuthResonseDTO(String tokenDeAcceso) {
		super();
		this.tokenDeAcceso = tokenDeAcceso;
	}

}
