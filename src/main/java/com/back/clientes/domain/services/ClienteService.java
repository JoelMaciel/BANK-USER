package com.back.clientes.domain.services;

import com.back.clientes.api.model.ClienteDto;
import com.back.clientes.domain.model.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    List<ClienteDto> listar();

    public void excluir(UUID clienteId);

    public Cliente buscarOuFalhar(UUID clienteId) ;
}
