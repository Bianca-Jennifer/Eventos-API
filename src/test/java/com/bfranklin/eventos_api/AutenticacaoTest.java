package com.bfranklin.eventos_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bfranklin.eventos_api.dto.UsuarioLoginDto;
import com.bfranklin.eventos_api.exception.ErrorMessage;
import com.bfranklin.eventos_api.jwt.JwtToken;

@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutenticacaoTest {
	@Autowired
	WebTestClient testClient;
	
	@Test
	public void Autenticacao_ComSucesso() {
		JwtToken responseBody = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("ana300@gmail.com", "123456"))
				.exchange().expectStatus().isOk().expectBody(JwtToken.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
	}
	
	@Test
	public void Autenticacao_ComCredenciaisInvalidas() {
		ErrorMessage responseBody = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("anainvalido@gmail.com", "123456"))
				.exchange().expectStatus().isEqualTo(400).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);
		
		ErrorMessage responseBody2 = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("ana300@gmail.com", "111111"))
				.exchange().expectStatus().isEqualTo(400).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(400);
			
			
	}
	
	@Test
	public void Autenticacao_ComEmailInvalido() {
		ErrorMessage responseBody = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("", "123456"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody2 = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("@gmail.com", "123456"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);
			
			
	}
	
	@Test
	public void Autenticacao_ComSenhaInvalida() {
		ErrorMessage responseBody = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("ana300@gmail.com", ""))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody2 = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("ana300@gmail.com", "123"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody3 = testClient.post().uri("/api/v1/auth").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioLoginDto("ana300@gmail.com", "1234567891011"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(422);
			
			
	}
}
