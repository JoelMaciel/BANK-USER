package com.back.clientes.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class ClienteDto {

    @NotNull
    private UUID clienteId;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String contato;

    @NotNull
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;


}
