package com.txview.accounts.config;

import com.txview.accounts.dao.AccountDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountsConfig {

    @Bean
    public AccountDao accountDao(Jdbi jdbi) {
        return jdbi.onDemand(AccountDao.class);
    }
}
