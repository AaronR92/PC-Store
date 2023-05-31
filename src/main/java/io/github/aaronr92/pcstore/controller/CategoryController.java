package io.github.aaronr92.pcstore.controller;

import io.github.aaronr92.pcstore.entity.Category;
import io.github.aaronr92.pcstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<Category> addNewCategory(
            @RequestBody Category category
    ) {
        category = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

}
