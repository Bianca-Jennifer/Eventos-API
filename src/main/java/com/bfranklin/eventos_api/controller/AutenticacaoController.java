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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Tag(name = "Autenticação", description = "Responsável por autenticar usuários")
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
	
	@Operation(summary = "Autenticar usuário", 
			responses = {
					@ApiResponse(responseCode = "200", description = "Autenticado com sucesso!", 
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = JwtToken.class))),
					@ApiResponse(responseCode = "400", description = "Credenciais inválidas",
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Campos inválidos",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
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
