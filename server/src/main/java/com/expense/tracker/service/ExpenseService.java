package com.expense.tracker.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.expense.tracker.model.Expense;
import com.expense.tracker.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public Expense createExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	public Optional<Expense> getExpenseById(String id) {
		return expenseRepository.findById(id);
	}
	
	public List<Expense> getAllExpenses(){
		return expenseRepository.findAll();
	}
	
	public Expense updateExpense(Expense expense) {
		return expenseRepository.save(expense);
	}
	
	public void removeExpense(String id) {
		expenseRepository.deleteById(id);
	}
	
	public void removeAllExpenses() {
		expenseRepository.deleteAll();
	}
}
