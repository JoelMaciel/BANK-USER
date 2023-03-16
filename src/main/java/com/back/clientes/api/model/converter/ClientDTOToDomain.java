package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.request.ClientDTO;
import com.back.clientes.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientDTOToDomain {

    private final ModelMapper modelMapper;

    public Client toDomainObject(ClientDTO clientDTO) {

       return modelMapper.map(clientDTO, Client.class);
    }

    public void copyToDomainObject(ClientDTO clientDTO, Client client) {
        modelMapper.map(clientDTO, client);
    }
}
