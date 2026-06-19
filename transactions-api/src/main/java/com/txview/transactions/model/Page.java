package com.txview.transactions.model;

import java.util.List;

public record Page<T>(
    List<T> content,
    long totalElements,
    int totalPages,
    int number,
    int size
) {
    public static <T> Page<T> of(List<T> content, long totalElements, int number, int size) {
        int totalPages = size == 0 ? 0 : (int) Math.ceil((double) totalElements / size);
        return new Page<>(content, totalElements, totalPages, number, size);
    }
}
