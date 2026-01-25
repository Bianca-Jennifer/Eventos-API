package com.bfranklin.eventos_api.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService; 
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);
		if(token == null || !token.startsWith(JwtUtils.JWT_BEARER)) {
			System.out.println("JWT Token está vazio ou não começa com 'Bearer '!");
			filterChain.doFilter(request, response);
			return;
		}
		
		if(!JwtUtils.isTokenValid(token)) {
			System.out.println("JWT Token inválido ou expirado!");
			filterChain.doFilter(request, response);
			return;
		}
		
		String email = JwtUtils.getEmailFromToken(token);
		toAuthenticate(request, email);
		
		filterChain.doFilter(request, response);
		return;
		
	}


	private void toAuthenticate(HttpServletRequest request, String email) {
		UserDetails user = jwtUserDetailsService.loadUserByUsername(email);
		
		UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken
				.authenticated(user, null, user.getAuthorities());
		
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	
}
