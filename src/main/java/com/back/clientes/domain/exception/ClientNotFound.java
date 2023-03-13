package com.back.clientes.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public class ClientNotFound extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ClientNotFound(String mensagem) {
        super(mensagem);
    }

    public ClientNotFound(UUID clientId) {
        this( String.format("There is no customer record with code %s", clientId));
    }

}
