package com.txview.transactions.model;

public class Transaction {

    private Long id;
    private String date;
    private String merchant;
    private Double amount;
    private String category;
    private Long accountId;
    private String rawDesc;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getMerchant() { return merchant; }
    public void setMerchant(String merchant) { this.merchant = merchant; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getRawDesc() { return rawDesc; }
    public void setRawDesc(String rawDesc) { this.rawDesc = rawDesc; }
}
