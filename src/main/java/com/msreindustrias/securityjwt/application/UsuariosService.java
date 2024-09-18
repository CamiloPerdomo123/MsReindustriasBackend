package com.msreindustrias.securityjwt.application;

import com.msreindustrias.securityjwt.application.dto.in.DatosPersonalesRequestDto;
import com.msreindustrias.securityjwt.application.port.input.IUsuariosService;
import com.msreindustrias.securityjwt.domain.entity.DatosPersonalesEntity;
import com.msreindustrias.securityjwt.domain.entity.UsuariosEntity;
import com.msreindustrias.securityjwt.infrastructure.repository.IDatosPersonalesRepository;
import com.msreindustrias.securityjwt.infrastructure.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UsuariosService implements IUsuariosService {

    @Autowired
    private IDatosPersonalesRepository datosPersonalesRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void guardarUsuario(DatosPersonalesRequestDto datosPersonalesRequestDto) {

        if (datosPersonalesRequestDto != null) {

            long idDatosPersonales = 0;
            boolean existeUsuario = usuarioRepository.existsUsuariosEntityByUsuario(datosPersonalesRequestDto.getNombreUsuario());
            boolean existeCorreo = usuarioRepository.existsByCorreo(datosPersonalesRequestDto.getCorreo());


            DatosPersonalesEntity datosPersonalesdb = datosPersonalesRepository.findFirstByNumeroDocumento(datosPersonalesRequestDto.getNumeroDocumento());

            DatosPersonalesEntity datosPersonalesEntity = null;
            DatosPersonalesEntity datosPersonalesEntityUpd = new DatosPersonalesEntity();

            if (datosPersonalesdb == null) {

                if (existeUsuario) {
                    throw new ResourceNotFoundException("El usuario ya existe");
                } else if (existeCorreo) {
                    throw new ResourceNotFoundException("El correo ya existe");
                }

                datosPersonalesEntity = new DatosPersonalesEntity();

                datosPersonalesEntity.setNombre(datosPersonalesRequestDto.getNombre());
                datosPersonalesEntity.setApellido(datosPersonalesRequestDto.getApellido());
                datosPersonalesEntity.setTipoDocumento(datosPersonalesRequestDto.getTipoDocumento());
                datosPersonalesEntity.setNumeroDocumento(datosPersonalesRequestDto.getNumeroDocumento());
                datosPersonalesEntity.setTelefono(datosPersonalesRequestDto.getTelefono());
                datosPersonalesEntity.setCiudad(datosPersonalesRequestDto.getCiudad());
                datosPersonalesEntity.setFechaCreacion(LocalDateTime.now());
                datosPersonalesEntity.setFechaActualizacion(LocalDateTime.now());

                datosPersonalesRepository.save(datosPersonalesEntity);
            } else {
                idDatosPersonales = datosPersonalesdb.getIdDatosPersonales();
                LocalDateTime fechaCreacion = datosPersonalesdb.getFechaCreacion();

                datosPersonalesEntityUpd.setIdDatosPersonales(idDatosPersonales);
                datosPersonalesEntityUpd.setNombre(datosPersonalesRequestDto.getNombre());
                datosPersonalesEntityUpd.setApellido(datosPersonalesRequestDto.getApellido());
                datosPersonalesEntityUpd.setTipoDocumento(datosPersonalesRequestDto.getTipoDocumento());
                datosPersonalesEntityUpd.setNumeroDocumento(datosPersonalesRequestDto.getNumeroDocumento());
                datosPersonalesEntityUpd.setTelefono(datosPersonalesRequestDto.getTelefono());
                datosPersonalesEntityUpd.setCiudad(datosPersonalesRequestDto.getCiudad());
                datosPersonalesEntityUpd.setFechaCreacion(fechaCreacion);
                datosPersonalesEntityUpd.setFechaActualizacion(LocalDateTime.now());

                datosPersonalesRepository.save(datosPersonalesEntityUpd);
            }

            guardarUsuarioPrivate(datosPersonalesRequestDto);
        }

    }

    void guardarUsuarioPrivate(DatosPersonalesRequestDto datosPersonalesRequest){

        long idRol = guardarRol(datosPersonalesRequest);
        long idUsuario = 0;

        boolean existeUsuario = usuarioRepository.existsUsuariosEntityByUsuario(datosPersonalesRequest.getNombreUsuario());
        DatosPersonalesEntity datosPersonalesdb = datosPersonalesRepository.findFirstByNumeroDocumento(datosPersonalesRequest.getNumeroDocumento());
        UsuariosEntity usuariosdb = usuarioRepository.findFirstByUsuario(datosPersonalesRequest.getNombreUsuario());
        UsuariosEntity usuariosEntityUpd = new UsuariosEntity();


        if(!existeUsuario) {
            UsuariosEntity usuariosEntity = null;
            usuariosEntity = new UsuariosEntity();


            usuariosEntity.setUsuario(datosPersonalesRequest.getNombreUsuario());
            usuariosEntity.setEstado("A");
            usuariosEntity.setIdRol(idRol);
            usuariosEntity.setIdPersona(datosPersonalesdb.getIdDatosPersonales());
            System.out.println("contrasena: " + (genearPassword(genearPassword(datosPersonalesRequest.getNumeroDocumento()))));
            System.out.println("contrasenaEncoder: " + (passwordEncoder.encode(genearPassword(datosPersonalesRequest.getNumeroDocumento()))));
            usuariosEntity.setPassword(passwordEncoder.encode(datosPersonalesRequest.getNumeroDocumento()));
            usuariosEntity.setCorreo(datosPersonalesRequest.getCorreo());
            usuariosEntity.setFechaCreacion(LocalDateTime.now());
            usuariosEntity.setFechaActualizacion(LocalDateTime.now());

            usuarioRepository.save(usuariosEntity);
        }else {

            idUsuario = usuariosdb.getIdUsuarios();
            LocalDateTime fechaCreacion = usuariosdb.getFechaCreacion();

            usuariosEntityUpd.setIdUsuarios(idUsuario);
            usuariosEntityUpd.setUsuario(datosPersonalesRequest.getNombreUsuario());
            usuariosEntityUpd.setEstado(usuariosEntityUpd.getEstado());
            usuariosEntityUpd.setIdRol(idRol);
            usuariosEntityUpd.setIdPersona(datosPersonalesdb.getIdDatosPersonales());
            usuariosEntityUpd.setPassword(usuariosEntityUpd.getPassword());
            usuariosEntityUpd.setFechaCreacion(fechaCreacion);
            usuariosEntityUpd.setFechaActualizacion(LocalDateTime.now());

            usuarioRepository.save(usuariosEntityUpd);
        }
    }

    public long guardarRol(DatosPersonalesRequestDto datosPersonalesRequestDto){
        long idRol = 0;
        if(datosPersonalesRequestDto.getRol() != null){
            if(datosPersonalesRequestDto.getRol().equals("ADMIN")){
                idRol = 1;
            } else if (datosPersonalesRequestDto.getRol().equals("EMPLEADO")) {
                idRol = 2;
            } else if (datosPersonalesRequestDto.getRol().equals("CLIENTE")) {
                idRol = 3;
            }
        }
        return idRol;
    }

    public String genearPassword(String numeroDocumento) {

        String password = numeroDocumento;

        return password;
    }


    @Override
    public List<DatosPersonalesEntity> listUsuarios() {

        List<DatosPersonalesEntity> datosPersonalesEntity = datosPersonalesRepository.findAll();

        return datosPersonalesEntity;
    }

    @Override
    public void actualizarUsuario(DatosPersonalesRequestDto datosPersonalesRequestDto) {

    }
}
