package com.txview.transactions.controller;

import com.txview.transactions.model.Transaction;
import com.txview.transactions.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository repo;

    public TransactionController(TransactionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Transaction> list(
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) String category) {
        if (accountId != null) return repo.findByAccountId(accountId);
        if (category != null) return repo.findByCategory(category);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }
}
