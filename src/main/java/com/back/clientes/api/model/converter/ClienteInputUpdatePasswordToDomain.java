package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.input.ClienteInputUpdate;
import com.back.clientes.api.model.input.ClienteInputUpdatePassword;
import com.back.clientes.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteInputUpdatePasswordToDomain {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInputUpdatePassword clienteUpdate) {

        return modelMapper.map(clienteUpdate, Cliente.class);
    }

    public void copyToDomainObject(ClienteInputUpdatePassword clienteUpdate, Cliente cliente) {
        modelMapper.map(clienteUpdate, cliente);
    }
}
