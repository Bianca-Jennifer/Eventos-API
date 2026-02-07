package com.bfranklin.eventos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfranklin.eventos_api.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {

}
