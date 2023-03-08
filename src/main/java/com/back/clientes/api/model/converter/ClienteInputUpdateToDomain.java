package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.input.ClienteInput;
import com.back.clientes.api.model.input.ClienteInputUpdate;
import com.back.clientes.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteInputUpdateToDomain {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInputUpdate clienteInputUpdate) {

        return modelMapper.map(clienteInputUpdate, Cliente.class);
    }

    public void copyToDomainObject(ClienteInputUpdate clienteInputUpdate, Cliente cliente) {
        modelMapper.map(clienteInputUpdate, cliente);
    }
}
