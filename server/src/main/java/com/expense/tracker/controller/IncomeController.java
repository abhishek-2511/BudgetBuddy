package com.expense.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expense.tracker.model.Income;
import com.expense.tracker.service.IncomeService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/get-incomes")
    public ResponseEntity<List<Income>> displayAllIncomes(){
    	return ResponseEntity.ok(incomeService.getAllIncomes());
    }
    
    @GetMapping("/get-incomeById/{id}")
    public ResponseEntity<Optional<Income>> getIncomes(@PathVariable String id) {
        return ResponseEntity.ok(incomeService.getIncomeById(id));
    }
    
    @PostMapping("/add-income")
    public ResponseEntity<Income> addIncome(@RequestBody @Valid Income income){
    	return ResponseEntity.ok(incomeService.createIncome(income));
    }
    
    @PutMapping("/update-incomeById/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable String id, @RequestBody @Valid Income income){
    	
    	if(income != null) {
			return ResponseEntity.ok(incomeService.updateIncome(income));
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
