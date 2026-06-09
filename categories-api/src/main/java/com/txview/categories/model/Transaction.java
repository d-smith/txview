package com.txview.categories.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private Long id;

    private String category;

    private Double amount;

    public Long getId() { return id; }
    public String getCategory() { return category; }
    public Double getAmount() { return amount; }
}
