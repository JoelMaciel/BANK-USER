package com.back.clientes.api.model;

import com.back.clientes.api.model.input.AddressInput;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class ClientDto {


    private UUID clientId;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
    private AddressInput address;


}
