package com.back.clientes.api.dtos.request;

import com.back.clientes.api.dtos.response.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserUpdateDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotNull
    @Valid
    private AddressDTO address;


}
