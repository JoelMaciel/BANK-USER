package com.back.clientes.domain.services;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilsService {

    String createUrl(UUID clientId, Pageable pageable);
}
