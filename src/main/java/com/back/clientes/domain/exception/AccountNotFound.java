package com.back.clientes.domain.exception;

import java.util.UUID;

public class AccountNotFound extends BusinessException {

    private static final long serialVersionUID = 1L;

    public AccountNotFound(String message) {
        super(message);
    }

    public AccountNotFound(UUID accountId) {
        this( String.format("The given account does not exist", accountId));
    }

}
