package com.back.clientes.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {


    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
}
