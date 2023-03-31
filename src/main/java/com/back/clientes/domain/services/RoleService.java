package com.back.clientes.domain.services;

import com.back.clientes.domain.enums.RoleType;
import com.back.clientes.domain.model.Roles;

public interface RoleService {
    Roles findByRoleName(RoleType roleType);
}
