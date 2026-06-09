package com.txview.bff.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class AccountsClient {

    private final RestClient restClient;

    public AccountsClient(@Value("${txview.api.accounts}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public List<Map<String, Object>> findAll() {
        return restClient.get()
                .uri("/accounts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
