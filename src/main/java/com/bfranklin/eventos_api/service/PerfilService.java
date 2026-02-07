package com.bfranklin.eventos_api.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfranklin.eventos_api.entity.Perfil;
import com.bfranklin.eventos_api.exception.exceptions.CpfUniqueViolationException;
import com.bfranklin.eventos_api.repository.PerfilRepository;

@Service
public class PerfilService {
	private final PerfilRepository perfilRepository;

	private PerfilService(PerfilRepository perfilRepository) {
		super();
		this.perfilRepository = perfilRepository;
	}
	
	@Transactional
	public Perfil salvar(Perfil perfil) {
		try {
			return perfilRepository.save(perfil);
		}catch(DataIntegrityViolationException ex) {
			throw new CpfUniqueViolationException(String.format("Cpf já cadastrado!"));
		}
	}	
}
