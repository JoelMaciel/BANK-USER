package com.back.clientes.domain.repository;

import com.back.clientes.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> , JpaSpecificationExecutor<Client> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

}
