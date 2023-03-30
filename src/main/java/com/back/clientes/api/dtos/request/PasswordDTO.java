package com.back.clientes.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class PasswordDTO {

    @NotBlank
    @Size(min = 8, max = 30)
    private String passwordCurrent;

    @NotBlank
    private String newPassword;

}
