package com.back.clientes.api.model.request;

import com.back.clientes.api.model.response.AddressDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClientDTOUpdate {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotNull
    @Valid
    private AddressDto address;
}
