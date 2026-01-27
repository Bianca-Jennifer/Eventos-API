package com.bfranklin.eventos_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfranklin.eventos_api.dto.UsuarioCreateDto;
import com.bfranklin.eventos_api.dto.UsuarioResponseDto;
import com.bfranklin.eventos_api.dto.UsuarioSenhaDto;
import com.bfranklin.eventos_api.dto.mapper.UsuarioMapper;
import com.bfranklin.eventos_api.entity.Usuario;
import com.bfranklin.eventos_api.exception.ErrorMessage;
import com.bfranklin.eventos_api.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuários", description = "Disponibiliza recursos para gerenciar usuários")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@Operation(summary = "Criar usuário", 
			responses = {
					@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso!", 
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = UsuarioResponseDto.class))),
					@ApiResponse(responseCode = "409", description = "Usuário com o e-mail já cadastrado no sistema",
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Dados de entrada inválidos,logo usuário não foi cadastrado",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@PostMapping
	public ResponseEntity<UsuarioResponseDto> create_user(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto){
		Usuario user = UsuarioMapper.toUsuario(usuarioCreateDto);
		Usuario user_criado = usuarioService.salvar_usuario(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toUsuarioResponseDto(user_criado));
	}
	
	
	@Operation(summary = "Buscar usuário por id", description = "Requisição exige um Bearer Token.Acesso restrito à ADMIN/USER.",
			security = @SecurityRequirement(name = "security"),
			responses = {
					@ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso!", 
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = UsuarioResponseDto.class))),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "403", description = "Usuário sem permissão de acesso",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') OR (hasRole('USER') AND #id == authentication.principal.id)")
	public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id){
		Usuario user = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(UsuarioMapper.toUsuarioResponseDto(user));
	}
	
	@Operation(summary = "Atualizar senha", 
			security = @SecurityRequirement(name = "security"), description = "Requisição exige um Bearer Token.Acesso restrito à ADMIN/USER.",
			responses = {
					@ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso!"),
					@ApiResponse(responseCode = "400", description = "Senha incorreta!",
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Campos inválidos",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "403", description = "Usuário sem permissão de acesso",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ErrorMessage.class)))
					
			}
	)
	@PatchMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER') AND #id == authentication.principal.id")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto usuario_senha){
		usuarioService.editarSenha(id, usuario_senha.getSenhaAtual(), usuario_senha.getSenhaNova(), usuario_senha.getSenhaConfirmada());
		return ResponseEntity.noContent().build();
	}
	
	
	@Operation(summary = "Listar usuários", description = "Requisição exige um Bearer Token.Acesso restrito à ADMIN.",
			security = @SecurityRequirement(name = "security"),
			responses = {
					@ApiResponse(responseCode = "200", description = "Lista de usuários!",
							content = @Content(mediaType = "application/json", 
							schema = @Schema(implementation = UsuarioResponseDto.class))),
					@ApiResponse(responseCode = "403", description = "Usuário sem permissão de acesso",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UsuarioResponseDto>> getALL(){
		List<Usuario> users = usuarioService.buscarTodos();
		return ResponseEntity.ok(UsuarioMapper.toListDto(users));
	}
	
}
