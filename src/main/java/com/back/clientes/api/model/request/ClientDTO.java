package com.back.clientes.api.model.request;

import com.back.clientes.api.model.response.AddressDto;
import com.back.clientes.domain.enums.ClientType;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ClientDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private ClientType clientType;

    @NotNull
    @Valid
    private AddressDto address;
}
