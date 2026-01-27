package com.bfranklin.eventos_api;

import java.util.function.Consumer;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bfranklin.eventos_api.dto.UsuarioLoginDto;
import com.bfranklin.eventos_api.jwt.JwtToken;

public class JwtAuthentication {

	public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient client, String email, String password){
		String token = client
				.post().uri("/api/v1/auth")
				.bodyValue(new UsuarioLoginDto(email, password))
				.exchange()
				.expectStatus().isOk()
				.expectBody(JwtToken.class)
				.returnResult()
				.getResponseBody()
				.getToken();
		
		return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
	}
}
