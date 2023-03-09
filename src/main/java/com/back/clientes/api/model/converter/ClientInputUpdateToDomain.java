package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.input.ClientInputUpdate;
import com.back.clientes.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInputUpdateToDomain {

    @Autowired
    private ModelMapper modelMapper;

    public Client toDomainObject(ClientInputUpdate clientInputUpdate) {

        return modelMapper.map(clientInputUpdate, Client.class);
    }

    public void copyToDomainObject(ClientInputUpdate clientInputUpdate, Client client) {
        modelMapper.map(clientInputUpdate, client);
    }
}
