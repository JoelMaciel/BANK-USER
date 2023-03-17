package com.back.clientes.domain.repository;

import com.back.clientes.domain.model.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, UUID> {
}
