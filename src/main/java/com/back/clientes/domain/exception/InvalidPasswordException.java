package com.back.clientes.domain.exception;

public class InvalidPasswordException extends BusinessException {

    private static final long serialVersionUID = 1L;
    public InvalidPasswordException(String mensagem) {
        super(mensagem);
    }
}
