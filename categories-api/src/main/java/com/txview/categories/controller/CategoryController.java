package com.txview.categories.controller;

import com.txview.categories.model.CategorySummary;
import com.txview.categories.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<CategorySummary> list() {
        return repo.findCategorySummaries();
    }
}
