package com.bfranklin.eventos_api.dto;

public class PerfilResponseDto {
	private Long id;
	private String nome;
	private String cpf;
	
	public PerfilResponseDto() {}
	
	public PerfilResponseDto(Long id, String nome, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
