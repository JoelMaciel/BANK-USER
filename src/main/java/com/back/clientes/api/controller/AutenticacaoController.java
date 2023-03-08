package com.back.clientes.api.controller;

import com.back.clientes.api.model.converter.ClienteInputToDomain;
import com.back.clientes.api.model.converter.ClienteToDto;
import com.back.clientes.api.model.ClienteDto;
import com.back.clientes.api.model.input.ClienteInput;
import com.back.clientes.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteInputToDomain clienteInputToDomain;

    @Autowired
    private ClienteToDto clienteToDto;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> registrarCliente(@RequestBody @Valid ClienteInput clienteInput) {

        if (clienteService.existeClienteCpf(clienteInput.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: Já existe um cliente cadastrado com este CPF.");
        }
        if (clienteService.existeClienteEmail(clienteInput.getEmail()) ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: Já existe um cliente cadastrado com este E-mail.");
        }
       var cliente = clienteInputToDomain.toDomainObject(clienteInput);
       cliente.setDataCriacao(OffsetDateTime.now());
       cliente.setDataAtualizacao(OffsetDateTime.now());

       var novoCliente = clienteService.salvar(cliente);

       var clienteDto = clienteToDto.converter(novoCliente);

       return  ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);

    }



}
