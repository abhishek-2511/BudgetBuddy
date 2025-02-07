package com.expense.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.model.Admin;
import com.expense.tracker.model.Users;
import com.expense.tracker.service.AdminService;
import com.expense.tracker.service.ExpenseService;
import com.expense.tracker.service.IncomeService;
import com.expense.tracker.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private IncomeService incomeService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Admin admin) {
		return adminService.register(admin);
	}
	
	@GetMapping("/get-allUsers")
	public List<Users> displayAllUsers() {
		return userService.getAllUsers();
	}
	
	
	@GetMapping("/get-allAdmin")
	public List<Admin> getAllAdmin() {
		return adminService.getAllAdmin();
	}
	
	@GetMapping("/getById/{id}")
	public Users getUserById(@PathVariable String id ) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/getByUsername/{username}")
	public Users getByUsername(@PathVariable String username ) {
		return userService.getByUsername(username);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Admin admin) {

		return adminService.login(admin);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody Admin admin){
		return adminService.forgotPassword(admin);
	}
	
	@GetMapping("/getTotalExpenses")
	public Double totalExpenses() {
		return expenseService.getTotalExpenses();
	}
	
	@GetMapping("/getTotalIncome")
	public Double totalIncome() {
		return incomeService.getTotalIncome();
	}
	
	@GetMapping("/getAllExpensesAndIncomes")
	public List<Object> getAllExpensesAndIncomes() {
		return adminService.getAllExpensesAndIncomes();
	}
}
