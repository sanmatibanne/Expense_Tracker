package com.sanmatibanne.ExpenseTracker.service;

import com.sanmatibanne.ExpenseTracker.dto.ExpenseDto;
import com.sanmatibanne.ExpenseTracker.entity.Category;
import com.sanmatibanne.ExpenseTracker.entity.Expense;
import com.sanmatibanne.ExpenseTracker.exceptions.ResourceNotFoundException;
import com.sanmatibanne.ExpenseTracker.mapper.ExpenseMapper;
import com.sanmatibanne.ExpenseTracker.repository.CategoryRepository;
import com.sanmatibanne.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        // Convert ExpenseDto to Expense entity
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);

        // save expense entity to database
        Expense savedExpense = expenseRepository.save(expense);

        // convert saved expense entity into ExpenseDto
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto expenseById(Long expenseId) {
        Expense expense =expenseRepository.findById(expenseId)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not found with id"));
       ExpenseDto expenseDto=ExpenseMapper.mapToExpenseDto(expense);
        return expenseDto;
    }

    @Override
    public List<ExpenseDto> getAllExpense() {
        List<Expense> expenses=expenseRepository.findAll();
        return expenses.stream().map(expense -> ExpenseMapper.mapToExpenseDto(expense)).collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(ExpenseDto expenseDto, Long expenseId) {
        Expense expense =expenseRepository.findById(expenseId).
                orElseThrow(()-> new ResourceNotFoundException("Expense not found with id"));

        expense.setAmount(expenseDto.amount());

        expense.setExpenseDate(expenseDto.expenseDate());

        if(expenseDto.categoryDto()!=null){
            Category category= categoryRepository.findById(expenseDto.categoryDto().id()).
                    orElseThrow(()->new ResourceNotFoundException("Category not found"));
            expense.setCategory(category);
        }
        Expense updatedExpense=expenseRepository.save(expense);

        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense =expenseRepository.findById(expenseId).
                orElseThrow(()-> new ResourceNotFoundException("Expense not found with id"));
        expenseRepository.deleteById(expenseId);
    }
}
