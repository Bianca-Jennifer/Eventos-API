package com.bfranklin.eventos_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfranklin.eventos_api.dto.UsuarioLoginDto;
import com.bfranklin.eventos_api.exception.ErrorMessage;
import com.bfranklin.eventos_api.jwt.JwtToken;
import com.bfranklin.eventos_api.jwt.JwtUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {
	private final JwtUserDetailsService jwtUserDetailsService;
	private final AuthenticationManager authenticationManager;
	
	
	public AutenticacaoController(JwtUserDetailsService jwtUserDetailsService,
			AuthenticationManager authenticationManager) {
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto usuario, HttpServletRequest request){
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword());
			authenticationManager.authenticate(authentication);
			JwtToken token = jwtUserDetailsService.getTokenAuthenticated(usuario.getEmail());
			return ResponseEntity.ok(token);
		}catch(AuthenticationException ex) {
			System.out.println("Credenciais inválidas");
		}
		return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais inválidas!"));
	}
	
	
	
}
