package com.back.clientes.api.model.input;

import com.back.clientes.api.model.EnderecoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteInput {

   // private UUID clienteId;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    private String senhaAntiga;

    @NotBlank
    private String contato;

    @NotNull
    @Valid
    private EnderecoInput endereco;
}
