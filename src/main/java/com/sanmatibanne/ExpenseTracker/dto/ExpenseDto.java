package com.sanmatibanne.ExpenseTracker.dto;

import com.sanmatibanne.ExpenseTracker.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(Long id,
                         BigDecimal amount,
                         LocalDate expenseDate,
                         CategoryDto categoryDto) {
}
