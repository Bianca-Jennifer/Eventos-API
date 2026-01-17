package com.bfranklin.eventos_api.entity;

import jakarta.persistence.*;


import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true, length = 100)
	private String username;
	
	@Column(name = "password", nullable = false, length = 200)
	private String password;
	
	@Column(name = "role", nullable = false, length = 25)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "data_criacao")
	private LocalDateTime data_criacao;
	
	@Column(name = "data_modificacao")
	private LocalDateTime data_modificacao;
	
	
	public enum Role {
		ROLE_ADMIN, ROLE_USER
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + "]";
	}
	
	
}
