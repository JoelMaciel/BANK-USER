package com.back.clientes.api.model.converter;

import com.back.clientes.api.model.ClientDto;
import com.back.clientes.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientToDto {

    @Autowired
    private ModelMapper modelMapper;

    public ClientDto converter(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    public List<ClientDto> toCollectionDto(List<Client> clients) {
        return  clients.stream()
                .map(client -> converter(client))
                .collect(Collectors.toList());

    }
        public Page<ClientDto> convertToPageDto(Page<Client> clientsPage, Pageable pageable) {
            Page<ClientDto> clientDtoPage = clientsPage.map(
                    client -> modelMapper.map(client, ClientDto.class));
            return clientDtoPage;
        }

}
