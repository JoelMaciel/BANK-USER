package com.back.clientes.core.security.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
