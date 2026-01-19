package com.bfranklin.eventos_api.dto;

public class UsuarioCreateDto {
	
	private String email;
	private String password;
	
	public UsuarioCreateDto() {}
	
	public UsuarioCreateDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioCreateDto [email=" + email + ", password=" + password + "]";
	}
	
	
}
