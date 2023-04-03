package com.back.clientes.api.clients;

import com.back.clientes.api.dtos.response.AccountDTO;
import com.back.clientes.api.dtos.response.ResponsePageDTO;
import com.back.clientes.domain.services.UtilsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
public class AccountClient {

    private final RestTemplate restTemplate;

    private final UtilsService utilsService;

    @Value("${bank.api.url.account}")
    String REQUEST_URI_ACCOUNT;

    @CircuitBreaker(name = "circuitbreakerInstance")
    public Page<AccountDTO> getAllAccountsByUser(UUID userId, Pageable pageable, String token) {
        List<AccountDTO> searchResult = null;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> requestEntity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<ResponsePageDTO<AccountDTO>> result = null;

        String url = REQUEST_URI_ACCOUNT + utilsService.createUrl(userId, pageable);

        log.debug("Request URL: {}", url);
        log.info("Request URL: {}", url);
        ParameterizedTypeReference<ResponsePageDTO<AccountDTO>> responseType =
                new ParameterizedTypeReference<ResponsePageDTO<AccountDTO>>() {};
        result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
        searchResult = result.getBody().getContent();

        log.info("Ending request / accounts userId {}", userId);

        return result.getBody();
    }

}
