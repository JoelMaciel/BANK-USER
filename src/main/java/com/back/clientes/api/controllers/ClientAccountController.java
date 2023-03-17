package com.back.clientes.api.controllers;

import com.back.clientes.api.clients.ClientRequestClient;
import com.back.clientes.api.model.response.AccountDTO;
import com.back.clientes.domain.model.ClientAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientAccountController {

    private final ClientRequestClient clientRequestClient;

    @GetMapping("/clients/{clientId}/accounts")
    public Page<AccountDTO> getAllAccountByClient(@PageableDefault(page = 0, size = 10, sort = "accountId",
            direction = Sort.Direction.ASC) Pageable pageable, @PathVariable(value = "clientId") UUID clientId){
    return clientRequestClient.getAllAccountsByClient(clientId, pageable);
    }
}
