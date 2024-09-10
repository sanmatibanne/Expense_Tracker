package com.sanmatibanne.ExpenseTracker.service;

import com.sanmatibanne.ExpenseTracker.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(Long categoryId,CategoryDto categoryDto);

    void deleteCategory(Long id);
}
