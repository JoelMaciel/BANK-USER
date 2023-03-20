package com.back.clientes.domain.services.impl;

import com.back.clientes.api.model.converters.ClientDTOToDomain;
import com.back.clientes.api.model.converters.ClientDTOUpdateToDomain;
import com.back.clientes.api.model.converters.ClientToDTO;
import com.back.clientes.api.model.request.ClientDTO;
import com.back.clientes.api.model.request.ClientDTOUpdate;
import com.back.clientes.api.model.request.EmployeeDTO;
import com.back.clientes.api.model.response.ClientSummaryDTO;
import com.back.clientes.domain.enums.ClientType;
import com.back.clientes.domain.exception.ClientNotFound;
import com.back.clientes.domain.exception.EntityInUseException;
import com.back.clientes.domain.exception.InvalidPasswordException;
import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.repository.ClientRepository;
import com.back.clientes.domain.services.ClientService;
import com.back.clientes.infrastructure.specification.SpecificationTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private static final String MSG_CLIENT_IN_USE =
            "Client code %s cannot be removed as it is in use";

    private final ClientRepository clientRepository;
    private final ClientDTOToDomain clientDTOToDomain;
    private final ClientDTOUpdateToDomain clientDTOUpdateToDomain;
    private final ClientToDTO clientToDTO;

    @Override
    public Page<ClientSummaryDTO> findAll(Specification<Client> spec, UUID accountId, Pageable pageable) {
        Page<Client> clientsPage = null;

        if(accountId != null) {
            clientsPage = clientRepository.findAll(SpecificationTemplate.clientAccountId(accountId).and(spec), pageable);
        } else {
            clientsPage = clientRepository.findAll(spec, pageable);
        }
        return clientToDTO.convertToPageDto(clientsPage, pageable);
    }

    public ClientSummaryDTO findByClient(UUID clientId) {
        var client = searchOrFail(clientId);
       return clientToDTO.converter(client);
    }

    @Transactional
    @Override
    public ClientSummaryDTO updateClient(UUID clientId , ClientDTOUpdate clientDTOUpdate) {
        Client client = searchOrFail(clientId);
        clientDTOUpdateToDomain.copyToDomainObject(clientDTOUpdate, client);
        return  clientToDTO.converter(clientRepository.save(client));

    }

    // nao está tratando DataIntegrityViolationException
    @Transactional
    @Override
    public ClientSummaryDTO save(ClientDTO clientDTO) {
        try {
            Client client = clientDTOToDomain.toDomainObject(clientDTO);
            return clientToDTO.converter(clientRepository.save(client));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("There is already a customer registered with this CPF or E-mail");
        }
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
    public void updatePassword(UUID clientId, String passwordCurrent, String newPassword) {
        Client client = searchOrFail(clientId);

        if (client.passwordDoesNotMatch(passwordCurrent)) {
            throw new InvalidPasswordException("Error: Mismatched old password");
        }
        client.setPassword(newPassword);
    }

    @Override
    public ClientSummaryDTO saveEmployee(EmployeeDTO employeeDTO) {
        var client = searchOrFail(employeeDTO.getClientId())
                .toBuilder()
                .clientType(ClientType.EMPLOYEE)
                .updateDate(OffsetDateTime.now())
                .build();
        clientRepository.save(client);
        return ClientSummaryDTO.fromDTO(client);
    }

    public Client searchOrFail(UUID clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFound(clientId));
    }


}

