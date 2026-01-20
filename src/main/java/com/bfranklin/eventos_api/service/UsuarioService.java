package com.bfranklin.eventos_api.service;

import java.util.List;

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
	
	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuário não encontrado!")
				
				);
	}
	
	@Transactional
	public Usuario editarSenha(long id, String senha_atual, String senha_nova, String senha_confirmada) {
		Usuario user = buscarPorId(id); //Persistente, terminando após o fim da requisição
		
		if(!user.getPassword().equals(senha_atual)) {
			throw new RuntimeException("Senha inválida!");
		}
		
		if(!senha_nova.equals(senha_confirmada)) {
			throw new RuntimeException("Senha nova não confere com a senha de confirmação!");
		}
		
		user.setPassword(senha_nova);
		return user;
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
}
