package com.msreindustrias.securityjwt.application.dto.out;

import com.msreindustrias.securityjwt.domain.entity.UsuariosEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JWTAuthResponseDto {
    private String tokeDeAcceso;
    private String tipoDeToken = "Bearer";
    private UsuariosEntity usuariosEntity;

    public JWTAuthResponseDto(String tokeDeAcceso,  UsuariosEntity usuariosEntity) {
        super();
        this.tokeDeAcceso = tokeDeAcceso;
        this.usuariosEntity=usuariosEntity;
    }
}
