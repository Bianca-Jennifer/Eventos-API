package com.bfranklin.eventos_api.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;



@Entity
@Table(name = "perfis")
@EntityListeners(AuditingEntityListener.class)
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;
	
	@OneToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@CreatedDate
	@Column(name = "data_criacao")
	private LocalDateTime data_criacao;
	
	@LastModifiedDate
	@Column(name = "data_modificacao")
	private LocalDateTime data_modificacao;
	
	@CreatedBy
	@Column(name = "criado_por")
	private String criado_por;
	
	@LastModifiedBy
	@Column(name = "modificado_por")
	private String modificado_por;

	public Perfil() {}

	public Perfil(Long id, String nome, String cpf, Usuario usuario, LocalDateTime data_criacao,
			LocalDateTime data_modificacao, String criado_por, String modificado_por) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.usuario = usuario;
		this.data_criacao = data_criacao;
		this.data_modificacao = data_modificacao;
		this.criado_por = criado_por;
		this.modificado_por = modificado_por;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
