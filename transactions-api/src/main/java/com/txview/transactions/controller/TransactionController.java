package com.txview.transactions.controller;

import com.txview.transactions.dao.TransactionDao;
import com.txview.transactions.model.Page;
import com.txview.transactions.model.Transaction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionDao transactionDao;

    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping
    public Page<Transaction> list(
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size) {
        int offset = page * size;
        if (accountId != null) {
            return Page.of(transactionDao.findByAccountId(accountId, size, offset),
                           transactionDao.countByAccountId(accountId), page, size);
        }
        if (category != null) {
            return Page.of(transactionDao.findByCategory(category, size, offset),
                           transactionDao.countByCategory(category), page, size);
        }
        return Page.of(transactionDao.findAll(size, offset),
                       transactionDao.countAll(), page, size);
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable Long id) {
        return transactionDao.findById(id).orElseThrow();
    }
}
