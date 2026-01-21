package com.bfranklin.eventos_api.exception.exceptions;

public class PasswordInvalidException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PasswordInvalidException(String mensagem) {
		super(mensagem);
	}
}
