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

	//post mapping for registration
	@PostMapping("/register")
	public ResponseEntity<?> signUp(@RequestBody Admin admin) {
		return adminService.register(admin);
	}
	
        //Get Mapping for display all users
	@GetMapping("/get-allUsers")
	public List<Users> displayAllUsers() {
		return userService.getAllUsers();
	}
	
	//Get Mapping for display all admins
	@GetMapping("/get-allAdmin")
	public List<Admin> getAllAdmin() {
		return adminService.getAllAdmin();
	}
	

        //Get User By Id
	@GetMapping("/getById/{id}")
	public Users getUserById(@PathVariable String id ) {
		return userService.getUserById(id);
	}
	

        //Get User Details By Username
	@GetMapping("/getUserByUsername/{username}")
	public Users getByUsername(@PathVariable String username ) {
		return userService.getByUsername(username);
	}
	
        //Post Mapping for login
	@PostMapping("/login")
	public ResponseEntity<?> signIn(@RequestBody Admin admin) {
		return adminService.login(admin);
	}
	

      	//Post mapping for forgot password
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> resetPassword(@RequestBody Admin admin){
		return adminService.forgotPassword(admin);
	}
	
 	//Get mapping for display total expenses
	@GetMapping("/getTotalExpenses")
	public Double getTotalExpenses() {
		return expenseService.getTotalExpenses();
	}

	
	//Get Mapping for display total income
	@GetMapping("/getTotalIncome")
	public Double getTotalIncomes() {
		return incomeService.getTotalIncome();
	}
	
	//Get mapping for display All expenses and incomes
	@GetMapping("/getAllExpensesAndIncomes")
	public List<Object> getAllExpensesAndIncomes() {
		return adminService.getAllExpensesAndIncomes();
	}
}
