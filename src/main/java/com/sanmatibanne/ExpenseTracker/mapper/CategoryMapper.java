package com.sanmatibanne.ExpenseTracker.mapper;

import com.sanmatibanne.ExpenseTracker.dto.CategoryDto;
import com.sanmatibanne.ExpenseTracker.entity.Category;

public class CategoryMapper {
        //map category entity to category dto
    public static Category mapToCategory(CategoryDto categoryDto){
       return new Category(categoryDto.id(),
               categoryDto.name()) ;

    }
    //map category dto to category entity
        public static CategoryDto mapToCategoryDto(Category category){
            return new CategoryDto(category.getId(),
                    category.getName()) ;

        }

}
