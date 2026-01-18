package com.bfranklin.eventos_api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfranklin.eventos_api.entity.Usuario;
import com.bfranklin.eventos_api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	public Usuario salvar_usuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
