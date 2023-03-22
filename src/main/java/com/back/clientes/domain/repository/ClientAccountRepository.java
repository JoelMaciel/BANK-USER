package com.back.clientes.domain.repository;

import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.model.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, UUID> {

    boolean existsByClientAndAccountId(Client client, UUID accountId);
    Optional<List<ClientAccount>> findByClientClientId(UUID accountId);

    boolean existsByAccountId(UUID accountId);

    void deleteByAccountId(UUID accountId);

}

