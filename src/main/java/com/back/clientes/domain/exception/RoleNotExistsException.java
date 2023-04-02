package com.back.clientes.domain.exception;

public class RoleNotExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RoleNotExistsException(String message) {
        super(message);
    }
}
