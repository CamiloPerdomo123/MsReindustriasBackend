package com.msreindustrias.securityjwt.application.security;

import com.msreindustrias.securityjwt.application.ResourceNotFoundException;
import com.msreindustrias.securityjwt.application.dto.in.LoginRequestDto;
import com.msreindustrias.securityjwt.application.dto.out.JWTAuthResponseDto;
import com.msreindustrias.securityjwt.application.port.input.IJwtService;
import com.msreindustrias.securityjwt.domain.entity.UsuariosEntity;
import com.msreindustrias.securityjwt.infrastructure.repository.IDatosPersonalesRepository;
import com.msreindustrias.securityjwt.infrastructure.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements IJwtService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProviderService jwtTokenProvider;


    @Override
    public JWTAuthResponseDto autenticacionUsuario(LoginRequestDto loginRequestDto) {

        // Buscar el usuario por nombre de usuario o correo
        UsuariosEntity usuariosEntity = usuarioRepository.findByUsuario(
                loginRequestDto.getUsernameOrEmail()
        );

        // Verificar si el usuario existe
        if (usuariosEntity != null) {

            // Verificar la contraseña
           // passwordEncoderUsuario(usuariosEntity, loginRequestDto);

            // Autenticar el usuario con el authenticationManager
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsernameOrEmail(), loginRequestDto.getPassword()));
            System.out.println("authentication: "+ authentication);

            // Establecer el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar el token JWT
            String token = jwtTokenProvider.generarToken(authentication);

            // Ocultar la contraseña en la respuesta
            usuariosEntity.setPassword("**********");

            // Devolver el DTO con el token y la información del usuario
            return new JWTAuthResponseDto(token, usuariosEntity);
        }else{
            throw new ResourceNotFoundException("No se encontro usuario ni correo");
        }

    }

    public boolean passwordEncoderUsuario(UsuariosEntity usuariosEntity, LoginRequestDto loginRequestDto){

        if(loginRequestDto.getUsernameOrEmail() != null){
            boolean passStatus = passwordEncoder.matches(loginRequestDto.getPassword(), usuariosEntity.getPassword());
            System.out.println("passwordEncoder: "+ passStatus);
            if(passStatus){
                return true;
            }else{
                throw new ResourceNotFoundException("Contraseña Incorrecta");
            }
        }
        return false;
    }

}
