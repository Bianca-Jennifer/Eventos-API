package com.bfranklin.eventos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfranklin.eventos_api.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
