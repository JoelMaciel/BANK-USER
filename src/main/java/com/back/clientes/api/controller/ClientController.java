package com.back.clientes.api.controller;

import com.back.clientes.api.model.ClientDto;
import com.back.clientes.api.model.converter.ClientInputToDomain;
import com.back.clientes.api.model.converter.ClientInputUpdatePasswordToDomain;
import com.back.clientes.api.model.converter.ClientInputUpdateToDomain;
import com.back.clientes.api.model.converter.ClientToDto;
import com.back.clientes.api.model.input.ClientInputUpdate;
import com.back.clientes.api.model.input.ClientInputUpdatePassword;
import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.services.ClientService;
import com.back.clientes.infrastructure.specification.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientToDto clientToDto;

    @Autowired
    private ClientInputToDomain clientInputToDomain;

    @Autowired
    private ClientInputUpdateToDomain clientInputUpdateToDomain;

    @Autowired
    private ClientInputUpdatePasswordToDomain clientUpdatePasswordToDomain;


    @GetMapping
    public Page<ClientDto> getAllClients(SpecificationTemplate.ClienteSpec spec,
                                  @PageableDefault(page = 0, size = 10, sort = "clientId", direction = Sort.Direction.ASC) Pageable pageable) {
        return clientService.findAll(spec, pageable);
    }

    @GetMapping("/{clientId}")
    public ClientDto getOneClient(@PathVariable UUID clientId) {
        Client client = clientService.searchOrFail(clientId);
        return  clientToDto.converter(client);
    }

    @PutMapping("/{clientId}")
    public ClientDto updateClient(@PathVariable UUID clientId ,
                               @RequestBody @Valid ClientInputUpdate clienteUpdate) {
        Client currentClient = clientService.searchOrFail(clientId);

        clientInputUpdateToDomain.copyToDomainObject(clienteUpdate, currentClient);
        currentClient.setUpdateDate(OffsetDateTime.now());
        var newClient = clientService.save(currentClient);

        return clientToDto.converter(newClient);
    }

    @PutMapping("/{clientId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable UUID clientId ,
                                                 @RequestBody @Valid ClientInputUpdatePassword clientUpdate) {
        Client client = clientService.searchOrFail(clientId);

        if(!client.getPassword().equals(clientUpdate.getOldPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password");
        }
        clientUpdatePasswordToDomain.copyToDomainObject(clientUpdate, client);
        client.setUpdateDate(OffsetDateTime.now());
        clientService.save(client);

        return ResponseEntity.status(HttpStatus.OK).body(clientToDto.converter(client)) ;

    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID clienteId) {
        clientService.delete(clienteId);
    }
}
