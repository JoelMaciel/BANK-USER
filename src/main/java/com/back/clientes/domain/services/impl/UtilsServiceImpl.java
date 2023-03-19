package com.back.clientes.domain.services.impl;

import com.back.clientes.domain.services.UtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsServiceImpl implements UtilsService {

    public String createUrl(UUID clientId, Pageable pageable) {
        return "/accounts?clientId=" + clientId + "&page=" + pageable.getPageNumber() + "&size="
           + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }

}
