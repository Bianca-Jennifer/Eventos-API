package com.bfranklin.eventos_api.dto;


public class UsuarioResponseDto {
	private Long id;
	private String email;
	private String role;
	
	public UsuarioResponseDto() {}

	public UsuarioResponseDto(Long id, String email, String role) {
		this.id = id;
		this.email = email;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UsuarioResponseDto [id=" + id + ", email=" + email + ", role=" + role + "]";
	}
	
	
	
}
