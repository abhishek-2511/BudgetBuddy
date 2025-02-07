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
	
	public Expense addExpense(Expense expense) {
		return expenseRepository.save(expense);
	}
	
    public List<Expense> getExpensesByUserId(String userId) {
        return expenseRepository.findByUserId(userId);
    }

	public Optional<Expense> getExpenseById(String id) {
		return expenseRepository.findById(id);
	}
	
	public List<Expense> getAllExpenses(){
		return expenseRepository.findAll();
	}
	
	public Double getTotalExpenses() {
		Double totalExpense = 0.0;
		for (Expense exp : getAllExpenses()) {
			totalExpense += exp.getAmount();
		}
		return totalExpense;
	}
	
    public Expense updateExpense(String id, Expense income) {
    	Optional<Expense> existsExpense = expenseRepository.findById(id);
    	if(existsExpense.isPresent()) {
    		existsExpense.get().setTitle(income.getTitle());
    		existsExpense.get().setAmount(income.getAmount());
    		existsExpense.get().setCategory(income.getCategory());
    		existsExpense.get().setDate(income.getDate());
    		existsExpense.get().setDescription(income.getDescription());
    		expenseRepository.save(existsExpense.get());
    		return existsExpense.get();
    	}
    	return null;
    }
	
	public void removeExpense(String id) {
		expenseRepository.deleteById(id);
	}
	
	public void removeAllExpenses() {
		expenseRepository.deleteAll();
	}
}
