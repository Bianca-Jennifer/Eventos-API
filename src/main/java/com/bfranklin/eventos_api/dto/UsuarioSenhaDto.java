package com.bfranklin.eventos_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioSenhaDto {
	
	@NotBlank
	@Size(min = 6, max = 12)
	private String senhaAtual;
	
	@NotBlank
	@Size(min = 6, max = 12)
	private String senhaNova;
	
	@NotBlank
	@Size(min = 6, max = 12)
	private String senhaConfirmada;
	
	public UsuarioSenhaDto() {}

	public UsuarioSenhaDto(String senhaAtual, String senhaNova, String senhaConfirmada) {
		this.senhaAtual = senhaAtual;
		this.senhaNova = senhaNova;
		this.senhaConfirmada = senhaConfirmada;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaConfirmada() {
		return senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}

	@Override
	public String toString() {
		return "UsuarioSenhaDto [senhaAtual=" + senhaAtual + ", senhaNova=" + senhaNova + ", senhaConfirmada="
				+ senhaConfirmada + "]";
	}

	
}
