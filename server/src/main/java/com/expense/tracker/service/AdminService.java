package com.expense.tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.expense.tracker.model.Admin;
import com.expense.tracker.model.Expense;
import com.expense.tracker.model.Income;
import com.expense.tracker.model.Users;
import com.expense.tracker.repository.AdminRepository;
import com.expense.tracker.repository.ExpenseRepository;
import com.expense.tracker.repository.IncomeRepository;
import com.expense.tracker.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
    @Autowired
    private IncomeRepository incomeRepository;
	
	public ResponseEntity<?> register(Admin admin) {

        if (userRepository.findByEmail(admin.getEmail()).isPresent()) {
            return new ResponseEntity<>("Admin already exist", HttpStatus.BAD_REQUEST);
        }
        adminRepository.save(admin); 
        return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> login(Admin admin) {
	        Optional<Admin>  existUser = adminRepository.findByUsername(admin.getUsername());
	        if(existUser.isEmpty())	
	        	existUser = adminRepository.findByEmail(admin.getEmail());
	        if (existUser.isPresent() && existUser.get().getPassword().equals(admin.getPassword())) {
			return new ResponseEntity<>(existUser, HttpStatus.OK);
	        } else {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
			
		}
	}
	
	public ResponseEntity<?> forgotPassword(Admin admin){
        Optional<Admin>  existUser = adminRepository.findByUsername(admin.getUsername());
        if(existUser.isEmpty())	
        	existUser = adminRepository.findByEmail(admin.getEmail());
        if (existUser.isPresent()) {
            String newPassword = admin.getPassword();
            existUser.get().setPassword(newPassword);
            adminRepository.save(existUser.get());
	        return new ResponseEntity<>(existUser, HttpStatus.OK);
        } else {
	            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
        }
	}
	
	public List<Object> getAllExpensesAndIncomes() {
	    List<Object> list = new ArrayList<>();
	    
	    // Fetch and add all expenses
	    List<Expense> expenseList = expenseRepository.findAll();
	    list.addAll(expenseList);
	    
	    // Fetch and add all incomes
	    List<Income> incomeList = incomeRepository.findAll();
	    list.addAll(incomeList);
	    
	    return list;
	}

	public List<Admin> getAllAdmin(){
		return adminRepository.findAll();
	}
	
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Users getByUsername(String usersname) {
		Users user = userRepository.findByUsername(usersname).get();
		return user;
	}
	
	public Users getUserById(String id){
		Users user = userRepository.findById(id).get();
		return user;
	}

}

