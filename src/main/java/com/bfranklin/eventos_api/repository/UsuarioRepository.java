package com.bfranklin.eventos_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bfranklin.eventos_api.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

	@Query("select u.role FROM Usuario u where u.email like :email")
	Usuario.Role findRoleByEmail(String email);

}
