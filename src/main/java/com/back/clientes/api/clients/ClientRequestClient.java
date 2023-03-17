package com.back.clientes.api.clients;

import com.back.clientes.api.model.response.AccountDTO;
import com.back.clientes.api.model.response.ResponsePageDTO;
import com.back.clientes.domain.services.UtilsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Component
public class ClientRequestClient {

    private final RestTemplate restTemplate;

    private final UtilsService utilsService;

    String REQUEST_URI = "http://localhost:8082";

    public Page<AccountDTO> getAllAccountsByClient(UUID clientId, Pageable pageable) {
        List<AccountDTO> searchResult = null;
        ResponseEntity<ResponsePageDTO<AccountDTO>> result = null;

        String url = utilsService.createUrl(clientId, pageable);

        log.debug("Request URL: {}", url);
        log.info("Request URL: {}", url);
        try {
            ParameterizedTypeReference<ResponsePageDTO<AccountDTO>> responseType =
                                 new ParameterizedTypeReference<ResponsePageDTO<AccountDTO>>() {};
            result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            searchResult = result.getBody().getContent();
        log.debug("Response Number of Elements: {}", searchResult.size());
        } catch (HttpStatusCodeException e) {
        log.error("Error request/ accounts {}", e);
        }
        log.info("Ending request / accounts clientId {}", clientId);

        return result.getBody();
    }
}
