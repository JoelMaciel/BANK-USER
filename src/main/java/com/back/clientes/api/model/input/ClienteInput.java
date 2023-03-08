package com.back.clientes.api.model.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteInput {

    private UUID clienteId;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String senhaAntiga;
    private String contato;
}
