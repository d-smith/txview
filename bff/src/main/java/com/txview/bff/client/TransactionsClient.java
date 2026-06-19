package com.txview.bff.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class TransactionsClient {

    private final RestClient restClient;

    public TransactionsClient(@Value("${txview.api.transactions}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public Map<String, Object> findAll(int page, int size) {
        return restClient.get()
                .uri("/transactions?page={page}&size={size}", page, size)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Map<String, Object> findByAccountId(Long accountId, int page, int size) {
        return restClient.get()
                .uri("/transactions?accountId={id}&page={page}&size={size}", accountId, page, size)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
