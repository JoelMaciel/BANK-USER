package com.back.clientes.domain.exception;

public class DuplicateDataException extends BusinessException {
    private static final long serialVersionUID = 1L;
    public DuplicateDataException(String mensagem) {
        super(mensagem);
    }

    public DuplicateDataException(String mensagem , Throwable cause) {
        super(mensagem, cause);
    }
}
