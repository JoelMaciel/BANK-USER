package com.back.clientes.domain.exception;

public class InvalidLoginDataException extends BusinessException {
    private static final long serialVersionUID = 1L;
    public InvalidLoginDataException(String mensagem) {
        super(mensagem);
    }

}
