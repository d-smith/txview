package com.txview.categories.dao;

import com.txview.categories.model.CategorySummary;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface CategoryDao {

    @SqlQuery("select category, count(*) as count, sum(amount) as total " +
              "from transactions group by category order by count(*) desc")
    @RegisterConstructorMapper(CategorySummary.class)
    List<CategorySummary> findCategorySummaries();
}
