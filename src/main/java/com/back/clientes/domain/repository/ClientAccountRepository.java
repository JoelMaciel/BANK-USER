package com.back.clientes.domain.repository;

import com.back.clientes.domain.model.Client;
import com.back.clientes.domain.model.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, UUID> {

    boolean existsByClientAndAccountId(Client client, UUID accountId);

    @Query(value = "select * from tb_clients_accounts where client_id = :clientId", nativeQuery = true)
    List<ClientAccount> findAllClientAccountIntoClient(@Param("clientId") UUID clientId);
}

