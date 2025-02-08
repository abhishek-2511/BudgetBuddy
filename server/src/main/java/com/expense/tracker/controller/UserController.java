package com.expense.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.model.Expense;
import com.expense.tracker.model.Users;
import com.expense.tracker.service.ExpenseService;
import com.expense.tracker.service.IncomeService;
import com.expense.tracker.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private IncomeService incomeService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users user) {
		return userService.register(user);
	}
	
	
	@GetMapping("/getById/{id}")
	public Users getUserById(@PathVariable String id ) {
		return userService.getUserById(id);
	}
	
	
	@DeleteMapping("/deleteExpenseOrIncomeById/{id}")
	public ResponseEntity<Expense> deleteExpense(@PathVariable String id){
		expenseService.removeExpense(id);
		incomeService.removeIncome(id);
		return ResponseEntity.ok().build(); 
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users user) {

		return userService.login(user);
	}
	

}
