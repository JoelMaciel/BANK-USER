package com.back.clientes.api.model.response;

import com.back.clientes.domain.enums.ClientType;
import com.back.clientes.domain.model.Client;
import lombok.Data;

@Data
public class ClientSummaryDTO {

    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private ClientType clientType;
    private AddressDto address;

    public static ClientSummaryDTO fromDTO(Client client) {
        ClientSummaryDTO dto = new ClientSummaryDTO();
        dto.setName(client.getName());
        dto.setCpf(client.getCpf());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setClientType(client.getClientType());
        dto.setAddress(AddressDto.fromDto(client.getAddress()));
        return dto;
    }
}
