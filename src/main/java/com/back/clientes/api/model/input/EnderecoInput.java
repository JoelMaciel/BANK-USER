package com.back.clientes.api.model.input;

import javax.validation.constraints.NotBlank;


public class EnderecoInput {

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;
}
