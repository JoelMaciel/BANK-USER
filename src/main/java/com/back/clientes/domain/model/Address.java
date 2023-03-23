package com.back.clientes.domain.model;

import com.back.clientes.api.model.response.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class Address {


    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;

    public static Address toEntity(AddressDTO dto) {
        return Address.builder()
                .zipCode(dto.getZipCode())
                .number(dto.getNumber())
                .street(dto.getStreet())
                .complement(dto.getComplement())
                .neighborhood(dto.getNeighborhood())
                .city(dto.getCity())
                .build();
    }
}

