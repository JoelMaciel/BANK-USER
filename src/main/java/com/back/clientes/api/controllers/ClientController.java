package com.back.clientes.api.controllers;

import com.back.clientes.api.model.converters.ClientToDTO;
import com.back.clientes.api.model.request.ClientDTOUpdate;
import com.back.clientes.api.model.request.PasswordDTO;
import com.back.clientes.api.model.response.ClientSummaryDTO;
import com.back.clientes.domain.services.ClientService;
import com.back.clientes.infrastructure.specification.SpecificationTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientToDTO clientToDTO;

    @GetMapping
    public Page<ClientSummaryDTO> getAllClients(SpecificationTemplate.ClientSpec spec,
         @PageableDefault(page = 0, size = 10, sort = "clientId", direction = Sort.Direction.ASC) Pageable pageable,
         @RequestParam(required = false) UUID accountId) {
        return clientService.findAll(spec, accountId, pageable);
    }

    @GetMapping("/{clientId}")
    public ClientSummaryDTO getOneClient(@PathVariable UUID clientId) {
        return  clientService.findByClient(clientId);
    }

    @PutMapping("/{clientId}")
    public ClientSummaryDTO updateClient(@PathVariable UUID clientId, @RequestBody @Valid ClientDTOUpdate clientUpdate) {
       return  clientService.updateClient(clientId, clientUpdate);
    }

    @PutMapping("/{clientId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable UUID clientId, @RequestBody @Valid PasswordDTO passwordDTO) {
        clientService.updatePassword(clientId, passwordDTO.getPasswordCurrent(), passwordDTO.getNewPassword());

    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID clientId) {
        clientService.delete(clientId);
    }
}
