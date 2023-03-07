package com.back.clientes.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Endereco {


    private String cep;
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    private String complemento;
    private String bairro;
    private String cidade;
}
