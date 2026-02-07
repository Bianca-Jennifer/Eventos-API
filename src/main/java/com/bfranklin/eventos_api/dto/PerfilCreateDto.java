package com.bfranklin.eventos_api.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class PerfilCreateDto {
	@NotNull
	@Size(min = 5, max = 100)
	private String nome;
	

	@Size(min = 11, max = 11)
	@CPF
	private String cpf;


	public PerfilCreateDto() {}


	public PerfilCreateDto(@NotNull @Size(min = 5, max = 100) String nome, @Size(min = 11, max = 11) @CPF String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	
}	