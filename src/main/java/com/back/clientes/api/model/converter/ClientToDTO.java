package com.back.clientes.api.model.converter;


import com.back.clientes.api.model.request.ClientDTO;
import com.back.clientes.api.model.response.ClientSummaryDTO;
import com.back.clientes.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientToDTO {
    private final ModelMapper modelMapper;

    public ClientSummaryDTO converter(Client client) {
        return modelMapper.map(client, ClientSummaryDTO.class);
    }

    public Page<ClientSummaryDTO> convertToPageDto(Page<Client> clientsPage, Pageable pageable) {
            Page<ClientSummaryDTO> clientDTOPage = clientsPage.map(
                    client -> modelMapper.map(client, ClientSummaryDTO.class));
            return clientDTOPage;
    }

}
