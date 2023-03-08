package com.back.clientes.domain.repository;

import com.back.clientes.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
