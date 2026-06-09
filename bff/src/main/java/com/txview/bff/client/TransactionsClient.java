package com.txview.bff.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class TransactionsClient {

    private final RestClient restClient;

    public TransactionsClient(@Value("${txview.api.transactions}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public List<Map<String, Object>> findAll() {
        return restClient.get()
                .uri("/transactions")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<Map<String, Object>> findByAccountId(Long accountId) {
        return restClient.get()
                .uri("/transactions?accountId={id}", accountId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
