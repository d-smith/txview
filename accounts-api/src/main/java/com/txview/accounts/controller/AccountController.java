package com.txview.accounts.controller;

import com.txview.accounts.model.Account;
import com.txview.accounts.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repo;

    public AccountController(AccountRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Account> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }
}
