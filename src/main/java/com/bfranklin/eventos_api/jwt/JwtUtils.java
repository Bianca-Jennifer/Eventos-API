package com.bfranklin.eventos_api.jwt;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;



@Component
public class JwtUtils {
	public static final String JWT_BEARER = "Bearer ";
	public static final String JWT_AUTHORIZATION = "Authorization";
	private static String SECRET_KEY;
	public static final long EXPIRE_DAYS = 0;
	public static final long EXPIRE_HOURS = 0;
	public static final long EXPIRE_MINUTES = 30;
	
	@Value("${jwt.secret}")
    private String jwtSecret;
	
	@PostConstruct
    public void init() {
        SECRET_KEY = jwtSecret; 
    }
	
	public JwtUtils() {}
	
	private static SecretKey generateKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}
	
	private static Date toExpireDate(Date start) {
		LocalDateTime dateTime_inicio = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateTime_final = dateTime_inicio.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
		return Date.from(dateTime_final.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static JwtToken createToken(String email, String role) {
		Date data_start = new Date();
		Date data_limite = toExpireDate(data_start);
		String token = Jwts.builder()
				.subject(email)
				.issuedAt(data_start)
				.expiration(data_limite)
				.signWith(generateKey())
				.claim("role", role)
				.compact();
		return new JwtToken(token);
	}
	
	private static Claims getClaimsFromToken(String token) {
		try {
			return Jwts.parser().verifyWith(generateKey()).build()
					.parseSignedClaims(refactorToken(token))        
	                .getPayload(); 
		}catch(JwtException ex) {
			System.out.println("Token inválido");
			return null;
		}
		
	}
	
	private static String refactorToken(String token) {
		if(token.contains(JWT_BEARER)) {
			return token.substring(JWT_BEARER.length());
		}
		return token;
	}
	
	public static String getEmailFromToken(String token) {
		return getClaimsFromToken(token).getSubject();
	}
	
	public static boolean isTokenValid(String token) {
		try {
			Jwts.parser().verifyWith(generateKey()).build()
			.parseSignedClaims(refactorToken(token)); 
			return true; 
		}catch(JwtException ex) {
			System.out.println("Token inválido");
			return false;
		}
	}
	

}
