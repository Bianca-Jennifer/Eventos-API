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
	
	@Column(name = "criado_por")
	private String criado_por;
	
	@Column(name = "modificado_por")
	private String modificado_por;
	
	public enum Role {
		ROLE_ADMIN, ROLE_USER
	}


	public Usuario() {}
	
	public Usuario(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public LocalDateTime getData_criacao() {
		return data_criacao;
	}


	public void setData_criacao(LocalDateTime data_criacao) {
		this.data_criacao = data_criacao;
	}


	public LocalDateTime getData_modificacao() {
		return data_modificacao;
	}


	public void setData_modificacao(LocalDateTime data_modificacao) {
		this.data_modificacao = data_modificacao;
	}

	public String getCriado_por() {
		return criado_por;
	}

	public void setCriado_por(String criado_por) {
		this.criado_por = criado_por;
	}

	public String getModificado_por() {
		return modificado_por;
	}

	public void setModificado_por(String modificado_por) {
		this.modificado_por = modificado_por;
	}
	
	
	
}
