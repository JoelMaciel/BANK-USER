package com.back.clientes.domain.services;

import com.back.clientes.api.model.ClientDto;
import com.back.clientes.api.model.input.ClientInputUpdate;
import com.back.clientes.api.model.input.PasswordIunput;
import com.back.clientes.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<ClientDto> listClient();

    public void delete(UUID clientId);

    Client save(Client client);

    ClientDto updateClient(UUID clientId ,ClientInputUpdate clientUpdate);


    Page<ClientDto> findAll(Specification<Client> spec, Pageable pageable);

    public Client searchOrFail(UUID clientId) ;

    public  boolean existsClientEmailOrCpf(String email, String cpf);

    public void updatePassword(UUID clientId, String passwordCurrent, String newPassword);
}
