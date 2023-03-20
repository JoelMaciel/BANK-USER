package com.back.clientes.api.model.response;

import com.back.clientes.domain.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private String zipCode;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    public static AddressDto fromDto(Address address) {
        return AddressDto.builder()
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .build();
    }


}
