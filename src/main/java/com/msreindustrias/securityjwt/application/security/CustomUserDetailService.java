package com.msreindustrias.securityjwt.application.security;

import com.msreindustrias.securityjwt.application.ResourceNotFoundException;
import com.msreindustrias.securityjwt.domain.entity.RolEntity;
import com.msreindustrias.securityjwt.domain.entity.UsuariosEntity;
import com.msreindustrias.securityjwt.infrastructure.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Optional<UsuariosEntity> usuariosOrCorreo =  usuarioRepository.findByUsuarioOrCorreo(usernameOrEmail, usernameOrEmail);

        if(usuariosOrCorreo != null){

        }else {
            throw new ResourceNotFoundException("Usuario no encontrado: " + usernameOrEmail);
        }

        return new User(usuariosOrCorreo.get().getCorreo(),usuariosOrCorreo.get().getPassword(), mapearRoles(usuariosOrCorreo.get().getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearRoles(Set<RolEntity> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombreRol())).collect(Collectors.toList());
    }
}
