package com.back.clientes.domain.services.impl;

import com.back.clientes.api.model.converter.ClientToDto;
import com.back.clientes.api.model.ClientDto;
import com.back.clientes.domain.exception.ClientNotFound;
import com.back.clientes.domain.exception.EntityInUseException;
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

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private static final String MSG_CLIENT_IN_USE =
            "Cliente de código %s não pode ser removido, pois está em uso";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientToDto clientToDto;

    @Override
    public List<ClientDto> listClient() {
        List<Client> clients = clientRepository.findAll();
        return  clientToDto.toCollectionDto(clients);
    }

    @Transactional
    @Override
    public void delete(UUID clientId) {
        try {
            clientRepository.deleteById(clientId);
            clientRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw  new ClientNotFound(clientId);

        } catch (DataIntegrityViolationException e) {
            throw  new EntityInUseException(
                    String.format(MSG_CLIENT_IN_USE, clientId));
        }
    }

    @Transactional
    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean existsClientCpf(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existsClientEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Override
    public Page<ClientDto> findAll(Specification<Client> spec, Pageable pageable) {
        Page<Client> clientsPage = clientRepository.findAll(spec,pageable);
        return clientToDto.convertToPageDto(clientsPage,pageable);
    }

    public Client searchOrFail(UUID clientId) {
        return  clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFound(clientId));
    }


}
