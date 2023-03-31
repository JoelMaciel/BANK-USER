package com.back.clientes.domain.services.impl;

import com.back.clientes.domain.enums.RoleType;
import com.back.clientes.domain.exception.RoleNotExistsException;
import com.back.clientes.domain.model.Roles;
import com.back.clientes.domain.repository.RoleRepository;
import com.back.clientes.domain.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private static final String MSG_ROLE_NOT_FOUND = "There is no role with that name saved in the database";
    private final RoleRepository roleRepository;

    @Override
    public Roles findByRoleName(RoleType roleType) {
        return roleRepository.findByRoleName(roleType)
                .orElseThrow(() -> new RoleNotExistsException(MSG_ROLE_NOT_FOUND));
    }
}
