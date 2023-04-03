package com.back.clientes.domain.exception;

public class PermissionDeniedException extends BusinessException {
    private static final long serialVersionUID = 1L;
    public PermissionDeniedException(String mensage) {
        super(mensage);
    }

}
