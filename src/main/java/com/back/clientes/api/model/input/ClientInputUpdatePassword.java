package com.back.clientes.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ClientInputUpdatePassword {

    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank
    private String oldPassword;

}
