package com.expense.tracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.model.Expense;
import com.expense.tracker.model.Income;
import com.expense.tracker.service.IncomeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/income")
@CrossOrigin
public class IncomeController {

    @Autowired
    private IncomeService incomeService; 

    @GetMapping("/get-all-incomes")
    public ResponseEntity<List<Income>> displayAllIncomes(){
    	return ResponseEntity.ok(incomeService.getAllIncomes());
    }
    
	@GetMapping("/get-total-income")
	public ResponseEntity<Double> getTotalIncome(){
		return ResponseEntity.ok(incomeService.getTotalIncome());
	}
    
    @GetMapping("/get-incomeById/{id}")
    public ResponseEntity<Optional<Income>> getIncomes(@PathVariable String id) {
		Optional<Income> income = incomeService.getIncomeById(id);
		if(income != null) {
			return ResponseEntity.ok(income);
		}
		return ResponseEntity.notFound().build();
    }
    
	@GetMapping("/get-incomeByUserId/{userId}")
	public ResponseEntity<List<Income>> getExpensesByUser(@PathVariable String userId){
		List<Income> income = incomeService.getIncomeByUserId(userId);
		if(income != null) {
			return ResponseEntity.ok(income);
		}
		return ResponseEntity.notFound().build();
	} 
    
    @PostMapping("/add-income")
    public ResponseEntity<Income> addIncome(@RequestBody @Valid Income income){
    	return ResponseEntity.ok(incomeService.addIncome(income));
    }
    
    @PutMapping("/update-incomeById/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable String id, @RequestBody @Valid Income income){
    	if(income != null) {
			return ResponseEntity.ok(incomeService.updateIncome(id, income));
		}
		return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Income> deleteIncome(@PathVariable String id){
    	incomeService.removeIncome(id);
    	return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllIncomes(){
    	incomeService.removeAllIncomes();
    	return ResponseEntity.ok().build();
    }
}
