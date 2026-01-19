package com.bfranklin.eventos_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfranklin.eventos_api.dto.UsuarioCreateDto;
import com.bfranklin.eventos_api.dto.UsuarioResponseDto;
import com.bfranklin.eventos_api.dto.mapper.UsuarioMapper;
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
	public ResponseEntity<UsuarioResponseDto> create_user(@RequestBody UsuarioCreateDto usuarioCreateDto){
		Usuario user = UsuarioMapper.toUsuario(usuarioCreateDto);
		Usuario user_criado = usuarioService.salvar_usuario(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toUsuarioResponseDto(user_criado));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id){
		Usuario user = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(user);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario){
		Usuario user = usuarioService.editarSenha(id, usuario.getPassword());
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getALL(){
		List<Usuario> users = usuarioService.buscarTodos();
		return ResponseEntity.ok(users);
	}
	
}
