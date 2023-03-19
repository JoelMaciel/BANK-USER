package com.back.clientes.api.model.response;

import com.back.clientes.domain.enums.ClientType;
import lombok.Data;

@Data
public class ClientSummaryDTO {

    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private ClientType clientType;
    private AddressDto address;


}
