package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.Category;
import com.hacknet.wheelsy.domain.repository.CategoryRepository;
import com.hacknet.wheelsy.domain.service.CategoryService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
    }

    @Override
    public Category createCategory(Category tag) {
        return categoryRepository.save(tag);
    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryDetails) {
        return categoryRepository.findById(categoryId).map(category -> {
            category.setName(categoryDetails.getName());
            return categoryRepository.save(category);
        }).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));

    }

    @Override
    public ResponseEntity<?> deleteCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).map(category -> {
            categoryRepository.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
    }
}
