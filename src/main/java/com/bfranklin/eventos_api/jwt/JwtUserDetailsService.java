package com.bfranklin.eventos_api.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bfranklin.eventos_api.entity.Usuario;
import com.bfranklin.eventos_api.service.UsuarioService;


public class JwtUserDetailsService implements UserDetailsService {
	private final UsuarioService usuarioService;
	
	
	public JwtUserDetailsService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.buscarPorEmail(username);
		return new JwtUserDetails(usuario);
	}
	
	public JwtToken getTokenAuthenticated(String email) {
		Usuario.Role role = usuarioService.BuscarRolePorEmail(email); 
		return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));
	}

}
