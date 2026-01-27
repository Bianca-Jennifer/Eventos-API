package com.bfranklin.eventos_api;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bfranklin.eventos_api.dto.UsuarioCreateDto;
import com.bfranklin.eventos_api.dto.UsuarioResponseDto;
import com.bfranklin.eventos_api.dto.UsuarioSenhaDto;
import com.bfranklin.eventos_api.exception.ErrorMessage;
import java.util.List;


@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioTest {
	@Autowired
	WebTestClient testClient;
	
	@Test
	public void criarUsuario_ComEmailEPasswordValidos() {
		UsuarioResponseDto responseBody = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("testando300@gmail.com", "123456"))
			.exchange().expectStatus().isCreated().expectBody(UsuarioResponseDto.class)
			.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getEmail()).isEqualTo("testando300@gmail.com");
		org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("USER");
		
	}
	
	@Test
	public void criarUsuario_ComEmailInvalido() {
		ErrorMessage responseBody = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("", "123456"))
			.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody2 = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioCreateDto("ana@", "123456"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody3 = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioCreateDto("ana@gmail.", "123456"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(422);
			
		
	}
	
	@Test
	public void criarUsuario_ComPasswordInvalido() {
		ErrorMessage responseBody = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("ana555@gmail.com", ""))
			.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody2 = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioCreateDto("ana444@gmail.com", "123"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody3 = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioCreateDto("ana333@gmail.com", "1234567891011122"))
				.exchange().expectStatus().isEqualTo(422).expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
		org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(422);
			
		
	}
	
	@Test
	public void criarUsuario_ComEmailRepetido() {
		ErrorMessage responseBody = testClient.post().uri("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("ana300@gmail.com", "123456"))
			.exchange().expectStatus().isEqualTo(409).expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
		
			
		
	}
	
	@Test
	public void BuscarUsuario_Existente() {
		UsuarioResponseDto responseBody = testClient.get().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.exchange()
				.expectStatus().isEqualTo(200).expectBody(UsuarioResponseDto.class).returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(300);
		org.assertj.core.api.Assertions.assertThat(responseBody.getEmail()).isEqualTo("ana300@gmail.com");
		org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");
		
		UsuarioResponseDto responseBody2 = testClient.get().uri("/api/v1/usuarios/301")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.exchange()
				.expectStatus().isEqualTo(200).expectBody(UsuarioResponseDto.class).returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getId()).isEqualTo(301);
		org.assertj.core.api.Assertions.assertThat(responseBody2.getEmail()).isEqualTo("ana301@gmail.com");
		org.assertj.core.api.Assertions.assertThat(responseBody2.getRole()).isEqualTo("USER");
		
		UsuarioResponseDto responseBody3 = testClient.get().uri("/api/v1/usuarios/301")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana301@gmail.com", "123456"))
				.exchange()
				.expectStatus().isEqualTo(200).expectBody(UsuarioResponseDto.class).returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody3.getId()).isEqualTo(301);
		org.assertj.core.api.Assertions.assertThat(responseBody3.getEmail()).isEqualTo("ana301@gmail.com");
		org.assertj.core.api.Assertions.assertThat(responseBody3.getRole()).isEqualTo("USER");
		
	}
	
	@Test
	public void BuscarUsuario_Inexistente() {
		ErrorMessage responseBody = testClient.get().uri("/api/v1/usuarios/1000")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.exchange()
				.expectStatus().isEqualTo(404).expectBody(ErrorMessage.class).returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
		
		
	}
	
	@Test
	public void BuscarUsuario_SemPermissao() {
		ErrorMessage responseBody = testClient.get().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana301@gmail.com", "123456"))
				.exchange()
				.expectStatus().isEqualTo(403).expectBody(ErrorMessage.class).returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
		
		
	}
	
	@Test
	public void AtualizarSenhaCorretamente() {
		testClient.patch().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("123456", "abcdefg", "abcdefg")).exchange()
				.expectStatus().isEqualTo(204);		
		
		testClient.patch().uri("/api/v1/usuarios/301")
			.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana301@gmail.com", "123456"))
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioSenhaDto("123456", "abcdefg", "abcdefg")).exchange()
			.expectStatus().isEqualTo(204);		

	}
	
	@Test
	public void AtualizarSenha_SemPermissao() {
		ErrorMessage responseBody = testClient.patch().uri("/api/v1/usuarios/301")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("123456", "abcdefg", "abcdefg")).exchange()
				.expectStatus().isEqualTo(403).expectBody(ErrorMessage.class).returnResult().getResponseBody();	
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
		
		ErrorMessage responseBody2 = testClient.patch().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana301@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("123456", "abcdefg", "abcdefg")).exchange()
				.expectStatus().isEqualTo(403).expectBody(ErrorMessage.class).returnResult().getResponseBody();	
		
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(403);
		
	}
	
	@Test
	public void AtualizarSenha_ComCamposInvalidos() {
		ErrorMessage responseBody = testClient.patch().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("", "", "")).exchange()
				.expectStatus().isEqualTo(422).expectBody(ErrorMessage.class).returnResult().getResponseBody();	
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody2 = testClient.patch().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("12345", "12345", "12345")).exchange()
				.expectStatus().isEqualTo(422).expectBody(ErrorMessage.class).returnResult().getResponseBody();	
		
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);
		
		ErrorMessage responseBody3 = testClient.patch().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("1234567891011222", "1234567891011222", "1234567891011222")).exchange()
				.expectStatus().isEqualTo(422).expectBody(ErrorMessage.class).returnResult().getResponseBody();	
		
		org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(422);
		
	}
	
	@Test
	public void AtualizarSenha_ComSenhasInvalidas() {
		ErrorMessage responseBody = testClient.patch().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("123456", "1234567", "1111111")).exchange()
				.expectStatus().isEqualTo(400).expectBody(ErrorMessage.class).returnResult().getResponseBody();	
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);
		
		ErrorMessage responseBody2 = testClient.patch().uri("/api/v1/usuarios/300")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioSenhaDto("1234566", "123456", "123456")).exchange()
				.expectStatus().isEqualTo(400).expectBody(ErrorMessage.class).returnResult().getResponseBody();	
		
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(400);
		
		
	}
	
	@Test
    public void listarUsuarios_Status200() {
        List<UsuarioResponseDto> responseBody = testClient
                .get()
                .uri("/api/v1/usuarios")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana300@gmail.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(3);
    }
	
	@Test
    public void listarUsuarios_SemPermissao() {
		ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/usuarios")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana301@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }
	
}
