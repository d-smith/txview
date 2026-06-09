package com.txview.bff.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class CategoriesClient {

    private final RestClient restClient;

    public CategoriesClient(@Value("${txview.api.categories}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public List<Map<String, Object>> findAll() {
        return restClient.get()
                .uri("/categories")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
