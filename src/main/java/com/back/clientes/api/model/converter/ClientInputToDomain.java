package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.input.ClientInput;
import com.back.clientes.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInputToDomain {

    @Autowired
    private ModelMapper modelMapper;

    public Client toDomainObject(ClientInput clientInput) {

        return modelMapper.map(clientInput, Client.class);
    }

    public void copyToDomainObject(ClientInput clientInput, Client cliente) {
        modelMapper.map(clientInput, cliente);
    }
}
