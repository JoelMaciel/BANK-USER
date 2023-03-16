package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.request.ClientDTOUpdate;
import com.back.clientes.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientDTOUpdateToDomain {
    private final ModelMapper modelMapper;

    public Client toDomainObject(ClientDTOUpdate clientDTOUpdate) {

        return modelMapper.map(clientDTOUpdate, Client.class);
    }

    public void copyToDomainObject(ClientDTOUpdate clientDTOUpdate, Client client) {
        modelMapper.map(clientDTOUpdate, client);
    }
}
