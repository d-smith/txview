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

    @SqlQuery("select id, date, merchant, amount, category, account_id as accountId, raw_desc as rawDesc from transactions order by id limit :limit offset :offset")
    @RegisterBeanMapper(Transaction.class)
    List<Transaction> findAll(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("select count(*) from transactions")
    long countAll();

    @SqlQuery("select id, date, merchant, amount, category, account_id as accountId, raw_desc as rawDesc from transactions where account_id = :accountId order by id limit :limit offset :offset")
    @RegisterBeanMapper(Transaction.class)
    List<Transaction> findByAccountId(@Bind("accountId") Long accountId, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("select count(*) from transactions where account_id = :accountId")
    long countByAccountId(@Bind("accountId") Long accountId);

    @SqlQuery("select id, date, merchant, amount, category, account_id as accountId, raw_desc as rawDesc from transactions where category = :category order by id limit :limit offset :offset")
    @RegisterBeanMapper(Transaction.class)
    List<Transaction> findByCategory(@Bind("category") String category, @Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery("select count(*) from transactions where category = :category")
    long countByCategory(@Bind("category") String category);
}
