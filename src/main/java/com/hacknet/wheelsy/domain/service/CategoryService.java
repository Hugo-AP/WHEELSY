package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    Page<Category> getAllCategories(Pageable pageable);
    Category getCategoryById(Long categoryId);
    Category createCategory(Category tag);
    Category updateCategory(Long categoryId, Category categoryDetails);
    ResponseEntity<?> deleteCategory(Long categoryId);
}
