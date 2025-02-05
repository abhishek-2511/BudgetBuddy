package com.expense.tracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.model.Expense;
import com.expense.tracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/get-expenses")
	public ResponseEntity<List<Expense>> displayAllExpenses(){
		return ResponseEntity.ok(expenseService.getAllExpenses());
	}
	
	@GetMapping("/get-expenseById/{id}")
	public ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable String id){
		Optional<Expense> expense = expenseService.getExpenseById(id);
		
		if(expense != null) {
			return ResponseEntity.ok(expense);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/add-expense")
	public ResponseEntity<Expense> addExpense(@RequestBody @Valid Expense expense){
		return ResponseEntity.ok(expenseService.createExpense(expense));
	}
	
	@PutMapping("/update-expenseById/{id}")
	public ResponseEntity<Expense> updateExpense(@PathVariable String id, @RequestBody @Valid Expense expense){
		if(expense != null) {
			return ResponseEntity.ok(expenseService.updateExpense(expense));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Expense> deleteExpense(@PathVariable String id){
		expenseService.removeExpense(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Void> deleteAllExpense(){
		expenseService.removeAllExpenses();
		return ResponseEntity.ok().build();
	}
	
	
	
}
