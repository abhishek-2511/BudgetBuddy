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

import com.expense.tracker.model.Users;
import com.expense.tracker.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users user) {
		return userService.register(user);
	}
	
	@GetMapping("/get-allUsers")
	public List<Users> displayAllUsers() {
		return userService.getAllUsers();
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
	public ResponseEntity<?> login(@RequestBody Users user) {

		return userService.login(user);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody Users user){
		return userService.forgotPassword(user);
	}
}
