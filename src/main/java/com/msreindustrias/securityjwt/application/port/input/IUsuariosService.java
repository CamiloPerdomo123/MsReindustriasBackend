package com.msreindustrias.securityjwt.application.port.input;

import com.msreindustrias.securityjwt.application.dto.in.DatosPersonalesRequestDto;
import com.msreindustrias.securityjwt.domain.entity.DatosPersonalesEntity;

import java.util.List;

public interface IUsuariosService {
    void guardarUsuario(DatosPersonalesRequestDto datosPersonalesRequestDto);
    List<DatosPersonalesEntity> listUsuarios();
    void actualizarUsuario(DatosPersonalesRequestDto datosPersonalesRequestDto);

}
