package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.request.ClientDTO;
import com.back.clientes.api.model.request.PasswordDTO;
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
    public Client toDomainObject(PasswordDTO passwordDTO) {

        return modelMapper.map(passwordDTO, Client.class);
    }

    public void copyToDomainObject(ClientDTO clientDTO, Client client) {
        modelMapper.map(clientDTO, client);
    }

    public void copyToDomainObject(PasswordDTO passwordDTO, Client client) {
        modelMapper.map(passwordDTO, client);
    }
}
