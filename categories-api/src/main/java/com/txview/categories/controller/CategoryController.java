package com.txview.categories.controller;

import com.txview.categories.dao.CategoryDao;
import com.txview.categories.model.CategorySummary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping
    public List<CategorySummary> list() {
        return categoryDao.findCategorySummaries();
    }
}
