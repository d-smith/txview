package com.txview.transactions.controller;

import com.txview.transactions.dao.TransactionDao;
import com.txview.transactions.model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionDao transactionDao;

    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping
    public List<Transaction> list(
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) String category) {
        if (accountId != null) return transactionDao.findByAccountId(accountId);
        if (category != null) return transactionDao.findByCategory(category);
        return transactionDao.findAll();
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable Long id) {
        return transactionDao.findById(id).orElseThrow();
    }
}
