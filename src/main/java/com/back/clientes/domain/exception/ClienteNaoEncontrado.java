package com.back.clientes.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontrado extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ClienteNaoEncontrado(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontrado(UUID clienteId) {
        this( String.format("Não existe um cadastro de cliente com código %s", clienteId));
    }

}
