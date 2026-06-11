package com.txview.transactions.dao;

import com.txview.transactions.model.Transaction;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {

    @SqlQuery("select id, date, merchant, amount, category, account_id as accountId, raw_desc as rawDesc from transactions")
    @RegisterBeanMapper(Transaction.class)
    List<Transaction> findAll();

    @SqlQuery("select id, date, merchant, amount, category, account_id as accountId, raw_desc as rawDesc from transactions where id = :id")
    @RegisterBeanMapper(Transaction.class)
    Optional<Transaction> findById(@Bind("id") Long id);

    @SqlQuery("select id, date, merchant, amount, category, account_id as accountId, raw_desc as rawDesc from transactions where account_id = :accountId")
    @RegisterBeanMapper(Transaction.class)
    List<Transaction> findByAccountId(@Bind("accountId") Long accountId);

    @SqlQuery("select id, date, merchant, amount, category, account_id as accountId, raw_desc as rawDesc from transactions where category = :category")
    @RegisterBeanMapper(Transaction.class)
    List<Transaction> findByCategory(@Bind("category") String category);
}
