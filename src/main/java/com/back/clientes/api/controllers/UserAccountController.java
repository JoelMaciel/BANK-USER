package com.back.clientes.api.controllers;

import com.back.clientes.api.clients.AccountClient;
import com.back.clientes.api.dtos.response.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserAccountController {

    private final AccountClient accountClient;

    @GetMapping("/users/{userId}/accounts")
    public Page<AccountDTO> getAllAccountByUser(@PageableDefault(page = 0, size = 10, sort = "accountId",
            direction = Sort.Direction.ASC) Pageable pageable, @PathVariable(value = "userId") UUID userId){
    return accountClient.getAllAccountsByUser(userId, pageable);
    }

}
