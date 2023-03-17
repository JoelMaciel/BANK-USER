package com.back.clientes.domain.services.impl;

import com.back.clientes.domain.repository.ClientAccountRepository;
import com.back.clientes.domain.services.ClientAccountService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientAccountServiceImpl implements ClientAccountService {

    private final ClientAccountRepository clientAccountRepository;
}
