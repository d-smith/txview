package com.txview.accounts.dao;

import com.txview.accounts.model.Account;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

public interface AccountDao {

    @SqlQuery("select id, name, type from accounts")
    @RegisterBeanMapper(Account.class)
    List<Account> findAll();

    @SqlQuery("select id, name, type from accounts where id = :id")
    @RegisterBeanMapper(Account.class)
    Optional<Account> findById(@Bind("id") Long id);
}
