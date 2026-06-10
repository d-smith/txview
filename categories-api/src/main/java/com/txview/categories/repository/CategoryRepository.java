package com.txview.categories.repository;

import com.txview.categories.model.CategorySummary;
import com.txview.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT new com.txview.categories.model.CategorySummary(t.category, COUNT(t), SUM(t.amount)) " +
           "FROM Transaction t GROUP BY t.category ORDER BY COUNT(t) DESC")
    List<CategorySummary> findCategorySummaries();
}
