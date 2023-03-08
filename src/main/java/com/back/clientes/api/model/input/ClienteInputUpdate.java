package com.back.clientes.api.model.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteInputUpdate {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String contato;

    @NotNull
    @Valid
    private EnderecoInput endereco;
}
