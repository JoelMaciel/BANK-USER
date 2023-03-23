package com.back.clientes.domain.exception;

import java.util.UUID;

public class UserNotFound extends BusinessException {

    private static final long serialVersionUID = 1L;

    public UserNotFound(String mensagem) {
        super(mensagem);
    }

    public UserNotFound(UUID clientId) {
        this( String.format("There is no customer record with code %s", clientId));
    }

}
