package com.back.clientes.api.controller;

import com.back.clientes.api.model.ClientDto;
import com.back.clientes.api.model.converter.ClientInputToDomain;
import com.back.clientes.api.model.converter.ClientToDto;
import com.back.clientes.api.model.input.ClientInput;
import com.back.clientes.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientInputToDomain clientInputToDomain;

    @Autowired
    private ClientToDto clientToDto;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto registerClient(@RequestBody @Valid ClientInput clientInput) {

       var cliente = clientInputToDomain.toDomainObject(clientInput);
       cliente.setCreationDate(OffsetDateTime.now());
       cliente.setUpdateDate(OffsetDateTime.now());

       var newClient = clientService.save(cliente);

       var clientDto = clientToDto.converter(newClient);

       return  clientDto;

    }
}
