package com.bfranklin.eventos_api.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfranklin.eventos_api.entity.Usuario;
import com.bfranklin.eventos_api.exception.exceptions.EmailUniqueViolationException;
import com.bfranklin.eventos_api.exception.exceptions.EntityNotFoundException;
import com.bfranklin.eventos_api.exception.exceptions.PasswordInvalidException;
import com.bfranklin.eventos_api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;	
	}
	
	@Transactional
	public Usuario salvar_usuario(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
		}catch(DataIntegrityViolationException ex) {
			throw new EmailUniqueViolationException(String.format("Usuário %s já existe!", usuario.getEmail()));
		}
	}
	
	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuário %d não encontrado!", id))
				
				);
	}
	
	@Transactional
	public Usuario editarSenha(long id, String senha_atual, String senha_nova, String senha_confirmada) {
		Usuario user = buscarPorId(id); //Persistente, terminando após o fim da requisição
		
		if(!user.getPassword().equals(senha_atual)) {
			throw new PasswordInvalidException("Senha inválida!");
		}
		
		if(!senha_nova.equals(senha_confirmada)) {
			throw new PasswordInvalidException("Senha nova não confere com a senha de confirmação!");
		}
		
		user.setPassword(senha_nova);
		return user;
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
}
