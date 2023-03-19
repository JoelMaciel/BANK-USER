package com.back.clientes.domain.services.impl;

import com.back.clientes.api.model.response.ClientAccountDTO;
import com.back.clientes.domain.exception.SubscriptionInUseException;
import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.model.ClientAccount;
import com.back.clientes.domain.repository.ClientAccountRepository;
import com.back.clientes.domain.services.ClientAccountService;
import com.back.clientes.domain.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientAccountServiceImpl implements ClientAccountService {

    private final ClientAccountRepository clientAccountRepository;
    private final ClientService clientService;

    @Override
    public ClientAccountDTO saveClientInAccount(UUID clientId, ClientAccountDTO clientAccountDTO) {
       var client = clientService.searchOrFail(clientId);

        if(existsByClientInAccountId(client, clientAccountDTO.getAccountId())) {
            throw new SubscriptionInUseException("A customer already exists for this account");
        }
            var clientAccount = save(client.converterToClientAccount(clientAccountDTO.getAccountId()));
        return ClientAccountDTO.converterToDTO(clientAccount);
    }

    @Override
    public ClientAccount save(ClientAccount clientAccount) {
        return clientAccountRepository.save(clientAccount);
    }

    @Override
    public boolean existsByClientInAccountId(Client client, UUID accountId) {
        return clientAccountRepository.existsByClientAndAccountId(client, accountId);
    }

}
