package fi.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.backend.dto.ExpenseDTO;
import fi.backend.entity.Expense;
import fi.backend.repositories.ExpenseRepository;

@Service
public class ExpenseServiceImplementation implements ExpenseService {
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	@Override
	public Expense addExpense(ExpenseDTO expenseDTO) {
		// TODO Auto-generated method stub
		
		try {
			Expense entityExpense = new Expense();
			
			BeanUtils.copyProperties(expenseDTO, entityExpense);
			
			expenseRepository.save(entityExpense);
			
			return entityExpense;
		}
		catch(BeansException e) {
			
			throw new RuntimeException("Error Creating an Expense : " + e.getLocalizedMessage());
		}
	}

	@Override
	public List<ExpenseDTO> getExpensesByUserId(String userId) {
		// TODO Auto-generated method stub
		
		List<ExpenseDTO> dtoList = new ArrayList<>();
		
		List<Expense> list = expenseRepository.findByUserId(userId);
		
		for(Expense eachExpense : list) {
			
			ExpenseDTO dto = new ExpenseDTO();
			
			BeanUtils.copyProperties(eachExpense, dto);
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public Optional<ExpenseDTO> getExpenseById(String id) {
		// TODO Auto-generated method stub
		
		Optional<Expense> expenseOptional = expenseRepository.findById(id);
		
		if(expenseOptional.isPresent()) {
			
			Expense entityExpense = expenseOptional.get();
			ExpenseDTO dto = new ExpenseDTO();
			
			BeanUtils.copyProperties(entityExpense, dto);
			
			//Return Optional<DTO> using 'of'
			return Optional.of(dto);
		}
		
		//Else return Empty if not Found
		return Optional.empty();
	}

	@Override
	public List<ExpenseDTO> getAllExpenses() {
		// TODO Auto-generated method stub
		
		List<ExpenseDTO> dtoList = new ArrayList<>();
		
		List<Expense> list = expenseRepository.findAll();
		
		for(Expense eachExpense : list) {
			
			ExpenseDTO dto = new ExpenseDTO();
			
			BeanUtils.copyProperties(eachExpense, dto);
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public Double getTotalExpenses() {
		// TODO Auto-generated method stub
		
		Double totalExpense = 0.0;
		
		for(ExpenseDTO expenseDTO : getAllExpenses()) {
			
			totalExpense += expenseDTO.getAmount();
		}
		
		return totalExpense;
	}

	@Override
	public ExpenseDTO updateExpense(String id, ExpenseDTO expenseDTO) {
		// TODO Auto-generated method stub
		
		Optional<Expense> existingExpense = expenseRepository.findById(id);
		
		if(existingExpense.isPresent()) {
			
			Expense entityExpense = existingExpense.get();
			
			// Update the fields from DTO
	        entityExpense.setTitle(expenseDTO.getTitle());
	        entityExpense.setAmount(expenseDTO.getAmount());
	        entityExpense.setCategory(expenseDTO.getCategory());
	        entityExpense.setDate(expenseDTO.getDate());
	        entityExpense.setDescription(expenseDTO.getDescription());
	        entityExpense.setUserId(expenseDTO.getUserId());
	        
	        //Saving the updated entity
	        Expense updatedExpense = expenseRepository.save(entityExpense);
	        
	        //Convert updated entity back to DTO
	        ExpenseDTO updatedDTO = new ExpenseDTO();
	        BeanUtils.copyProperties(updatedExpense, updatedDTO);
	        
	        //Return updated DTO
	        return updatedDTO;
		}
		
		throw new RuntimeException("Expense With ID : " + id + " Not found...");
	}

	@Override
	public void removeExpense(String id) {
		// TODO Auto-generated method stub
		if (!expenseRepository.existsById(id)) {
		    throw new RuntimeException("Expense with ID " + id + " not found.");
		}
		
		expenseRepository.deleteById(id);
	}

	@Override
	public void removeAllExpenses() {
		// TODO Auto-generated method stub
		
		expenseRepository.deleteAll();
	}

}
