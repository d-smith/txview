package com.txview.transactions.config;

import com.txview.transactions.dao.TransactionDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionsConfig {

    @Bean
    public TransactionDao transactionDao(Jdbi jdbi) {
        return jdbi.onDemand(TransactionDao.class);
    }
}
