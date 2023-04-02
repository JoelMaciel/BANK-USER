package com.back.clientes.domain.repository;

import com.back.clientes.domain.enums.RoleType;
import com.back.clientes.domain.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Roles, UUID> {

    Optional<Roles> findByRoleName(RoleType roleType);
}
