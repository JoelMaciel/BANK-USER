package com.back.clientes.domain.repository;

import com.back.clientes.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> , JpaSpecificationExecutor<Cliente> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    //Page<Cliente> findAllSpec(Specification<Cliente> spec , Pageable pageable);
}
