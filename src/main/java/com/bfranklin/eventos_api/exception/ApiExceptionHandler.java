package com.bfranklin.eventos_api.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bfranklin.eventos_api.exception.exceptions.EmailUniqueViolationException;
import com.bfranklin.eventos_api.exception.exceptions.EntityNotFoundException;
import com.bfranklin.eventos_api.exception.exceptions.PasswordInvalidException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MethodArgumentNotValidException;



@RestControllerAdvice
public class ApiExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){

		return ResponseEntity.status(HttpStatusCode.valueOf(422)).contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatusCode.valueOf(422), "Campo(s) inválido(s)!", result));
	}
	
	@ExceptionHandler(EmailUniqueViolationException.class)
	public ResponseEntity<ErrorMessage> dataIntegrityViolationException(RuntimeException ex, HttpServletRequest request){

		return ResponseEntity.status(HttpStatusCode.valueOf(409)).contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatusCode.valueOf(409), ex.getMessage()));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex, HttpServletRequest request){

		return ResponseEntity.status(HttpStatusCode.valueOf(404)).contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatusCode.valueOf(404), ex.getMessage()));
	}
	
	@ExceptionHandler(PasswordInvalidException.class)
	public ResponseEntity<ErrorMessage> passwordInvalidException(RuntimeException ex, HttpServletRequest request){

		return ResponseEntity.status(HttpStatusCode.valueOf(400)).contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatusCode.valueOf(400), ex.getMessage()));
	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException ex, HttpServletRequest request){

		return ResponseEntity.status(HttpStatusCode.valueOf(403)).contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(request, HttpStatusCode.valueOf(403), ex.getMessage()));
	}
	
}
