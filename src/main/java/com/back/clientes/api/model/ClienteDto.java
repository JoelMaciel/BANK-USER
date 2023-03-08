package com.back.clientes.api.model;

import com.back.clientes.api.model.input.EnderecoInput;
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


    private UUID clienteId;
    private String nome;
    private String cpf;
    private String contato;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;
    private EnderecoInput endereco;


}
