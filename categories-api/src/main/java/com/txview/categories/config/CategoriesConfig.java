package com.txview.categories.config;

import com.txview.categories.dao.CategoryDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriesConfig {

    @Bean
    public CategoryDao categoryDao(Jdbi jdbi) {
        return jdbi.onDemand(CategoryDao.class);
    }
}
