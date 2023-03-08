package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.input.ClienteInput;
import com.back.clientes.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteInputToDomain {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }

    public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
        modelMapper.map(clienteInput, cliente);
    }
}
