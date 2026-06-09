package com.txview.categories.model;

public class CategorySummary {
    private String category;
    private Long count;
    private Double total;

    public CategorySummary(String category, Long count, Double total) {
        this.category = category;
        this.count = count;
        this.total = total;
    }

    public String getCategory() { return category; }
    public Long getCount() { return count; }
    public Double getTotal() { return total; }
}
