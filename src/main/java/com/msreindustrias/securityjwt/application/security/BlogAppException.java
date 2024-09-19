package com.msreindustrias.securityjwt.application.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class BlogAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus estado;
	private String mensaje;

	public BlogAppException(HttpStatus estado, String mensaje) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
	}

}
