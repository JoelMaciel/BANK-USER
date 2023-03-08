package com.back.clientes.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class ClienteInputUpdatePassword {

    @NotBlank
    private String senha;

    @NotBlank
    private String senhaAntiga;

}
