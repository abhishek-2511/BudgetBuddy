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

import fi.backend.dto.IncomeDTO;
import fi.backend.services.IncomeService;
import jakarta.validation.Valid;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/income")
public class IncomeController {
	
	@Autowired
	IncomeService incomeService;
	
	@GetMapping("/get-all-incomes")
	public ResponseEntity<List<IncomeDTO>> displayAllIncome(){
		
		return ResponseEntity.ok(incomeService.getAllIncomes());
	}
	
	@GetMapping("/get-total-income")
	public ResponseEntity<Double> getTotalIncome(){
		
		return ResponseEntity.ok(incomeService.getTotalIncome());
	}
	
	@GetMapping("/get-incomeById/{id}")
    public ResponseEntity<Optional<IncomeDTO>> getIncomes(@PathVariable String id) {
		
		Optional<IncomeDTO> income = incomeService.getIncomeById(id);
		
		if(income != null) {
			
			return ResponseEntity.ok(income);
		}
		
		return ResponseEntity.notFound().build();
    }
	
	@GetMapping("/get-incomeByUserId/{userId}")
	public ResponseEntity<List<IncomeDTO>> getExpensesByUser(@PathVariable String userId){
		
		List<IncomeDTO> income = incomeService.getIncomeByUserId(userId);
		
		if(income != null) {
			
			return ResponseEntity.ok(income);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/add-income")
    public ResponseEntity<IncomeDTO> addIncome(@RequestBody @Valid IncomeDTO dto){
		
    	return ResponseEntity.ok(incomeService.addIncome(dto));
    }
	
	@PutMapping("/update-incomeById/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable String id, @RequestBody @Valid IncomeDTO dto){
    	
		if(dto != null) {
			
			return ResponseEntity.ok(incomeService.updateIncome(id, dto));
		}
		
		return ResponseEntity.notFound().build();
    }
	
	@DeleteMapping("/deleteById/{id}")
    public ResponseEntity<IncomeDTO> deleteIncome(@PathVariable String id){
    	
		incomeService.removeIncomeById(id);
    	
		return ResponseEntity.ok().build();
    }
	
	@DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllIncomes(){
    	
		incomeService.removeAllIncomes();
    	
		return ResponseEntity.ok().build();
    }
}
