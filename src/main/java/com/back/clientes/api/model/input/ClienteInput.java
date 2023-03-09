package com.back.clientes.api.model.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteInput {


    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 30)
    private String senha;

    @NotBlank
    private String contato;

    @NotNull
    @Valid
    private EnderecoInput endereco;
}
