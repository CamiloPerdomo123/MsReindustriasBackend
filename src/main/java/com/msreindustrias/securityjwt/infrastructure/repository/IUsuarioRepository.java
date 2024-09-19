package com.msreindustrias.securityjwt.infrastructure.repository;

import com.msreindustrias.securityjwt.application.dto.out.UsuarioResponseDto;
import com.msreindustrias.securityjwt.domain.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuariosEntity, Long> {
    boolean existsUsuariosEntityByUsuario(String usuario);
    boolean existsByCorreo(String correo);
    UsuariosEntity findFirstByUsuario(String usuario);
    Optional<UsuariosEntity> findByCorreo(String correo);
    Optional<UsuariosEntity> findByUsuarioOrCorreo(String usuario, String correo);
    Optional<UsuariosEntity> findByUsuario(String usuario);
    boolean existsByUsuario(String usuario);

   /* @Query("SELECT new com.msreindustrias.securityjwt.application.dto.out.UsuarioResponseDto(" +
            "dp.nombre, dp.apellido, dp.tipoDocumento, dp.numeroDocumento, dp.telefono, " +
            "u.correo, dp.ciudad, u.usuario, r.nombreRol) " +
            "FROM UsuariosEntity u " +
            "JOIN DatosPersonalesEntity dp ON u.idPersona = dp.idDatosPersonales " +
            "JOIN RolEntity r ON u.idRol = r.idRol")
    List<UsuarioResponseDto> obtenerUsuariosConDatosPersonalesYRol();*/


}
