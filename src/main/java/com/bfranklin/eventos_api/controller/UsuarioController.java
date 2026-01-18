package com.bfranklin.eventos_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfranklin.eventos_api.entity.Usuario;
import com.bfranklin.eventos_api.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create_user(@RequestBody Usuario usuario){
		//System.out.println("PASSWORD: " + usuario.getPassword());
		Usuario user = usuarioService.salvar_usuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
}
