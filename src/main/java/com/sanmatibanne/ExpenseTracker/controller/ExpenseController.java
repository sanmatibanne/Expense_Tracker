package com.sanmatibanne.ExpenseTracker.controller;

import com.sanmatibanne.ExpenseTracker.dto.ExpenseDto;
import com.sanmatibanne.ExpenseTracker.service.ExpenseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;
    // Build create expense REST API
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){

        ExpenseDto savedExpense = expenseService.createExpense(expenseDto);

        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long expenseId){
        ExpenseDto expense= expenseService.expenseById(expenseId);
        return ResponseEntity.ok(expense);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(){
       List<ExpenseDto>  expense= expenseService.getAllExpense();
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/update/{expenseId}")
    public ResponseEntity<ExpenseDto> updateExpense(@RequestBody ExpenseDto expenseDto,@PathVariable Long expenseId){
        ExpenseDto  expense= expenseService.updateExpense(expenseDto,expenseId);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId){
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("Expense is deleted");
    }
}
