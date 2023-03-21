package com.back.clientes.api.controllers;

import com.back.clientes.api.clients.AccountRequestClient;
import com.back.clientes.api.model.response.AccountDTO;
import com.back.clientes.api.model.response.ClientAccountDTO;
import com.back.clientes.domain.services.ClientAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientAccountController {

    private final AccountRequestClient accountRequestClient;

    private final ClientAccountService clientAccountService;

    @GetMapping("/clients/{clientId}/accounts")
    public Page<AccountDTO> getAllAccountByClient(@PageableDefault(page = 0, size = 10, sort = "accountId",
            direction = Sort.Direction.ASC) Pageable pageable, @PathVariable(value = "clientId") UUID clientId){
    return accountRequestClient.getAllAccountsByClient(clientId, pageable);
    }

    @PostMapping("/clients/{clientId}/accounts/subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientAccountDTO saveSubscriptionClientInAccount(@PathVariable(value = "clientId") UUID clientId,
                                          @RequestBody @Valid ClientAccountDTO clientAccountDTO) {
       return clientAccountService.saveClientInAccount(clientId, clientAccountDTO);
    }

    @DeleteMapping("/clients/accounts/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientAccountByAccount(@PathVariable(value = "accountId") UUID accountId) {
      clientAccountService.deleteClientAccount(accountId);
    }

}
