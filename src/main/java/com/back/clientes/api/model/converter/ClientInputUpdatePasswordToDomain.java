package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.input.PasswordIunput;
import com.back.clientes.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInputUpdatePasswordToDomain {

    @Autowired
    private ModelMapper modelMapper;

    public Client toDomainObject(PasswordIunput clientUpdate) {

        return modelMapper.map(clientUpdate, Client.class);
    }

    public void copyToDomainObject(PasswordIunput clientUpdate, Client client) {
        modelMapper.map(clientUpdate, client);
    }
}
