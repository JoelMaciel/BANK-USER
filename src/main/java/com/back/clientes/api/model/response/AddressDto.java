package com.back.clientes.api.model.response;

import lombok.Data;

@Data
public class AddressDto {

    private String zipCode;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

}
