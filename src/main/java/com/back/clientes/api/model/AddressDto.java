package com.back.clientes.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private String zipCode;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private AddressDto address;

}
