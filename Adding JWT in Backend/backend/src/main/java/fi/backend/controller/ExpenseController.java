package fi.backend.controller;

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

import fi.backend.dto.ExpenseDTO;
import fi.backend.entity.Expense;
import fi.backend.services.ExpenseService;
import jakarta.validation.Valid;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	@Autowired
	ExpenseService expenseService;
	
	@GetMapping("/get-all-expenses")
	public ResponseEntity<List<ExpenseDTO>> displayAllExpenses(){
		
		return ResponseEntity.ok(expenseService.getAllExpenses());
	}
	
	@GetMapping("/get-total-expenses")
	public ResponseEntity<Double> getTotalExpenses(){
		
		return ResponseEntity.ok(expenseService.getTotalExpenses());
	}
	
	@GetMapping("/get-expenseById/{id}")
	public ResponseEntity<Optional<ExpenseDTO>> getExpenseById(@PathVariable String id){
		
		Optional<ExpenseDTO> expenseDTO = expenseService.getExpenseById(id);
		
		if(expenseDTO != null) {
			
			return ResponseEntity.ok(expenseDTO);
		}
		
		return ResponseEntity.notFound().build();
	} 
	
	@GetMapping("/get-expensesByUserId/{userId}")
	public ResponseEntity<List<ExpenseDTO>> getExpensesByUserId(@PathVariable String userId){
		
		List<ExpenseDTO> expense = expenseService.getExpensesByUserId(userId);
		
		if(expense != null) {
			
			return ResponseEntity.ok(expense);
		}
		
		return ResponseEntity.notFound().build();
	} 
 	
	@PostMapping("/add-expense")
	public ResponseEntity<Expense> addExpense(@RequestBody @Valid ExpenseDTO expense){
		
		return ResponseEntity.ok(expenseService.addExpense(expense));
	}
	
	@PutMapping("/update-expenseById/{id}")
	public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable String id, @RequestBody @Valid ExpenseDTO expenseDTO){
		
		if(expenseDTO != null) {
			
			return ResponseEntity.ok(expenseService.updateExpense(id, expenseDTO));
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
