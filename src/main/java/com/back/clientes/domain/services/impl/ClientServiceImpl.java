package com.back.clientes.domain.services.impl;

import com.back.clientes.api.model.ClientDto;
import com.back.clientes.api.model.converter.ClientInputUpdateToDomain;
import com.back.clientes.api.model.converter.ClientToDto;
import com.back.clientes.api.model.input.ClientInputUpdate;
import com.back.clientes.domain.exception.ClientNotFound;
import com.back.clientes.domain.exception.EntityInUseException;
import com.back.clientes.domain.exception.InvalidPasswordException;
import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.repository.ClientRepository;
import com.back.clientes.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private static final String MSG_CLIENT_IN_USE =
            "Client code %s cannot be removed as it is in use";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientInputUpdateToDomain clientInputUpdateToDomain;

    @Autowired
    private ClientToDto clientToDto;

    @Override
    public Page<ClientDto> findAll(Specification<Client> spec, Pageable pageable) {
        Page<Client> clientsPage = clientRepository.findAll(spec, pageable);
        return clientToDto.convertToPageDto(clientsPage, pageable);
    }

    @Override
    public List<ClientDto> listClient() {
        List<Client> clients = clientRepository.findAll();
        return clientToDto.toCollectionDto(clients);
    }

    @Transactional
    @Override
    public void delete(UUID clientId) {
        try {
            clientRepository.deleteById(clientId);
            clientRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new ClientNotFound(clientId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_CLIENT_IN_USE, clientId));
        }
    }

    @Transactional
    @Override
    public Client save(Client client) {

        if (existsClientEmailOrCpf(client.getEmail(), client.getCpf())) {
            throw new EntityInUseException("There is already a customer registered with this cpf or email");
        }
        return clientRepository.save(client);
    }

    @Override
    public ClientDto updateClient(UUID clientId ,ClientInputUpdate clientUpdate) {
        Client currentClient = searchOrFail(clientId);
        clientInputUpdateToDomain.copyToDomainObject(clientUpdate, currentClient);
        currentClient.setUpdateDate(OffsetDateTime.now());
        var newClient = clientRepository.save(currentClient);
        var clientDto = clientToDto.converter(newClient);
        return  clientDto;
    }

    @Transactional
    @Override
    public void updatePassword(UUID clientId, String passwordCurrent, String newPassword) {
        Client client = searchOrFail(clientId);

        if (client.passwordDoesNotMatch(passwordCurrent)) {
            throw new InvalidPasswordException("Error: Mismatched old password");
        }
        client.setPassword(newPassword);
    }

    public Client searchOrFail(UUID clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFound(clientId));
    }

    @Override
    public boolean existsClientEmailOrCpf(String email, String cpf) {
        return clientRepository.existsByEmailOrCpf(email, cpf);
    }


}

