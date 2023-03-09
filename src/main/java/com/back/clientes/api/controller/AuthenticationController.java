package com.back.clientes.api.controller;

import com.back.clientes.api.model.converter.ClientInputToDomain;
import com.back.clientes.api.model.converter.ClientToDto;
import com.back.clientes.api.model.input.ClientInput;
import com.back.clientes.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> registerClient(@RequestBody @Valid ClientInput clientInput) {

        if (clientService.existsClientCpf(clientInput.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: There is already a client registered with this CPF.");
        }
        if (clientService.existsClientEmail(clientInput.getEmail()) ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: There is already a client registered with this email.");
        }
       var cliente = clientInputToDomain.toDomainObject(clientInput);
       cliente.setCreationDate(OffsetDateTime.now());
       cliente.setUpdateDate(OffsetDateTime.now());

       var newClient = clientService.save(cliente);

       var clientDto = clientToDto.converter(newClient);

       return  ResponseEntity.status(HttpStatus.CREATED).body(clientDto);

    }
}
