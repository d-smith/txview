package com.txview.accounts.controller;

import com.txview.accounts.dao.AccountDao;
import com.txview.accounts.model.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping
    public List<Account> list() {
        return accountDao.findAll();
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return accountDao.findById(id).orElseThrow();
    }
}
