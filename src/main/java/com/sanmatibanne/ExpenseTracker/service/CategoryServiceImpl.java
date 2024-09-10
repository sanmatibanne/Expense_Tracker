package com.sanmatibanne.ExpenseTracker.service;

import com.sanmatibanne.ExpenseTracker.dto.CategoryDto;
import com.sanmatibanne.ExpenseTracker.entity.Category;
import com.sanmatibanne.ExpenseTracker.exceptions.ResourceNotFoundException;
import com.sanmatibanne.ExpenseTracker.mapper.CategoryMapper;
import com.sanmatibanne.ExpenseTracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category= CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory=categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category=categoryRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Category not found with id"+id));
        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> lists=categoryRepository.findAll();
        return lists.stream().map((list) ->CategoryMapper.mapToCategoryDto(list)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId,CategoryDto categoryDto) {
        Category category=categoryRepository.findById(categoryId).
                orElseThrow(()-> new ResourceNotFoundException("Category not found with id"+categoryId));
        category.setName(categoryDto.name());
       Category updatedCategory= categoryRepository.save(category);
        return   CategoryMapper.mapToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category=categoryRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Category not found with id"+id));
        categoryRepository.delete(category);
    }
}
