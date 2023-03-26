package com.back.clientes.domain.exception;

public class InvalidDataException extends BusinessException {
    private static final long serialVersionUID = 1L;
    public InvalidDataException(String mensagem) {
        super(mensagem);
    }

    public InvalidDataException(String mensagem , Throwable cause) {
        super(mensagem, cause);
    }
}
