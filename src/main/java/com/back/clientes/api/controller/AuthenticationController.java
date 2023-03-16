package com.back.clientes.api.controller;

import com.back.clientes.api.model.request.ClientDTO;
import com.back.clientes.api.model.response.ClientSummaryDTO;
import com.back.clientes.domain.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final ClientService clientService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientSummaryDTO registerClient(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }
}
