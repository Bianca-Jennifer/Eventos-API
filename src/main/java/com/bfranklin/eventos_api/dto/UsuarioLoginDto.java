package com.bfranklin.eventos_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDto {
	@NotBlank
	@Email(message = "Formato do e-mail inválido!", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
	private String email;
	@NotBlank
	@Size(min = 6, max = 12)
	private String password;
	
	
	public UsuarioLoginDto() {}

	public UsuarioLoginDto(
			@NotBlank @Email(message = "Formato do e-mail inválido!", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$") String email,
			@NotBlank @Size(min = 6, max = 12) String password) {
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
		return "UsuarioLoginDto [email=" + email + ", password=" + password + "]";
	}
	
	
	
}
