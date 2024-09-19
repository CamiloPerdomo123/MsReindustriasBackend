package com.msreindustrias.securityjwt.application.security;

import com.msreindustrias.securityjwt.application.ResourceNotFoundException;
import com.msreindustrias.securityjwt.application.dto.in.LoginRequestDto;
import com.msreindustrias.securityjwt.application.dto.out.JWTAuthResponseDto;
import com.msreindustrias.securityjwt.domain.entity.UsuariosEntity;
import com.msreindustrias.securityjwt.infrastructure.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public JWTAuthResponseDto authenticateUser(LoginRequestDto login) {

        Optional<UsuariosEntity> usuario = usuarioRepository.findByUsuarioOrCorreo(login.getUsernameOrEmail(),login.getUsernameOrEmail());

        if(usuario != null){
            JWTAuthResponseDto jwtAuthResponseDto = new JWTAuthResponseDto();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsernameOrEmail(), login.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            //obtenemos el token del jwtTokenProvider
            String token = jwtTokenProvider.generarToken(authentication);

            jwtAuthResponseDto.setTokeDeAcceso(token);
            jwtAuthResponseDto.setUsuariosEntity(usuario.get());

            return jwtAuthResponseDto;
        }else {
            throw new ResourceNotFoundException("El usuario no existe");
        }

    }
}
