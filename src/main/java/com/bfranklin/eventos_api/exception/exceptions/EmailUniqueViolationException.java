package com.bfranklin.eventos_api.exception.exceptions;

public class EmailUniqueViolationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmailUniqueViolationException(String mensagem) {
		super(mensagem);
	}
}
