package com.msreindustrias.securityjwt.application.security;

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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioRepository usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		UsuariosEntity usuario = usuarioRepositorio.findByUsuarioOrCorreo(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));

		return new User(usuario.getCorreo(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<RolEntity> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombreRol())).collect(Collectors.toList());
	}
}
