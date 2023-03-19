package com.back.clientes.domain.services;

import com.back.clientes.api.model.response.ClientAccountDTO;
import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.model.ClientAccount;

import java.util.UUID;

public interface ClientAccountService {

    boolean existsByClientInAccountId(Client client, UUID accountId);

    ClientAccountDTO saveClientInAccount(UUID clientId, ClientAccountDTO clientAccountDTO);

    ClientAccount save(ClientAccount clientAccount);
}
