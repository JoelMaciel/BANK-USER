package com.back.clientes.api.model.response;

import lombok.Data;

@Data
public class ClientSummaryDTO {

    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private AddressDto address;


}
