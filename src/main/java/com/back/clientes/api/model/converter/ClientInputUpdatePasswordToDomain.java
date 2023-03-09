package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.input.ClientInputUpdatePassword;
import com.back.clientes.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInputUpdatePasswordToDomain {

    @Autowired
    private ModelMapper modelMapper;

    public Client toDomainObject(ClientInputUpdatePassword clientUpdate) {

        return modelMapper.map(clientUpdate, Client.class);
    }

    public void copyToDomainObject(ClientInputUpdatePassword clientUpdate, Client client) {
        modelMapper.map(clientUpdate, client);
    }
}
