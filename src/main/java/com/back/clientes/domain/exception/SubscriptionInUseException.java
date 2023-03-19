package com.back.clientes.domain.exception;

public class SubscriptionInUseException extends BusinessException {
    private static final long serialVersionUID = 1L;
    public SubscriptionInUseException(String mensagem) {
        super(mensagem);
    }
}
