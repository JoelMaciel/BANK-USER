package com.back.clientes.api.controllers;

import com.back.clientes.api.clients.AccountClient;
import com.back.clientes.api.dtos.response.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserAccountController {

    private final AccountClient accountClient;

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("/users/{userId}/accounts")
    public Page<AccountDTO> getAllAccountByUser(@PageableDefault(page = 0, size = 10, sort = "accountId",
            direction = Sort.Direction.ASC) Pageable pageable, @PathVariable(value = "userId") UUID userId,
                                                @RequestHeader("Authorization") String token){
    return accountClient.getAllAccountsByUser(userId, pageable, token);
    }

}
