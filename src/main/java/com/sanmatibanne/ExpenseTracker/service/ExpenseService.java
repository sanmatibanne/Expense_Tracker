package com.sanmatibanne.ExpenseTracker.service;

import com.sanmatibanne.ExpenseTracker.dto.ExpenseDto;
import jakarta.persistence.Entity;

import java.util.List;

public interface ExpenseService {

    ExpenseDto createExpense(ExpenseDto expenseDto);

    ExpenseDto expenseById(Long id);

    List<ExpenseDto> getAllExpense();

    ExpenseDto updateExpense(ExpenseDto expenseDto,Long expenseId);

    void deleteExpense(Long expenseId );
}
