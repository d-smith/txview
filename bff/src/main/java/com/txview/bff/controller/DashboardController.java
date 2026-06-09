package com.txview.bff.controller;

import com.txview.bff.client.AccountsClient;
import com.txview.bff.client.CategoriesClient;
import com.txview.bff.client.TransactionsClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final AccountsClient accounts;
    private final TransactionsClient transactions;
    private final CategoriesClient categories;

    public DashboardController(AccountsClient accounts,
                               TransactionsClient transactions,
                               CategoriesClient categories) {
        this.accounts = accounts;
        this.transactions = transactions;
        this.categories = categories;
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        return Map.of(
            "accounts", accounts.findAll(),
            "categories", categories.findAll()
        );
    }

    @GetMapping("/accounts")
    public List<Map<String, Object>> accounts() {
        return accounts.findAll();
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<Map<String, Object>> transactionsByAccount(@PathVariable Long id) {
        return transactions.findByAccountId(id);
    }

    @GetMapping("/categories")
    public List<Map<String, Object>> categories() {
        return categories.findAll();
    }
}
