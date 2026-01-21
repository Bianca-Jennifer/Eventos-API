package com.bfranklin.eventos_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorMessage {
	private String path;
	private String method;
	private int status;
	private String mensagem;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String,String> errors;
	
	
	public ErrorMessage() {
	}

	public ErrorMessage(HttpServletRequest request, HttpStatusCode status, String mensagem) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.mensagem = mensagem;
	
	}
	
	public ErrorMessage(HttpServletRequest request, HttpStatusCode status, String mensagem, BindingResult result) {
		this.path = request.getRequestURI();
		this.method = request.getMethod();
		this.status = status.value();
		this.mensagem = mensagem;
		addErrors(result);
	
	}

	private void addErrors(BindingResult result) {
		this.errors = new HashMap<>();
		
		for(FieldError fieldError : result.getFieldErrors()) {
			this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorMessage [path=" + path + ", method=" + method + ", status=" + status + ", mensagem=" + mensagem
				+ ", errors=" + errors + "]";
	}

	
	
	
}
