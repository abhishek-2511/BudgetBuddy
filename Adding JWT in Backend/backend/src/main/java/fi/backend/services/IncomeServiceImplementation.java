package fi.backend.services;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.backend.dto.ExpenseDTO;
import fi.backend.dto.IncomeDTO;
import fi.backend.entity.Expense;
import fi.backend.entity.Income;
import fi.backend.repositories.IncomeRepository;

@Service
public class IncomeServiceImplementation implements IncomeService {

	@Autowired
	IncomeRepository incomeRepository;
	
	@Override
	public IncomeDTO addIncome(IncomeDTO incomeDTO) {
		// TODO Auto-generated method stub
		
		Income income = new Income();
		
		// Copy properties from DTO to entity
	    BeanUtils.copyProperties(incomeDTO, income);
	    
	    // Save entity to the database
	    Income savedIncome = incomeRepository.save(income);
	    
	    // Convert saved entity back to DTO
	    IncomeDTO savedIncomeDTO = new IncomeDTO();
	    BeanUtils.copyProperties(savedIncome, savedIncomeDTO);
	    
	    return savedIncomeDTO;
	}

	@Override
	public List<IncomeDTO> getIncomeByUserId(String userId) {
		// TODO Auto-generated method stub
		
		List<IncomeDTO> result = new ArrayList<>();
		
		List<Income> list = incomeRepository.findByUserId(userId);
		
		for(Income income : list) {
			
			IncomeDTO dto = new IncomeDTO();
			
			BeanUtils.copyProperties(income, dto);
			
			result.add(dto);
		}
		
		return result;
	}

	@Override
	public Optional<IncomeDTO> getIncomeById(String id) {
		// TODO Auto-generated method stub
		
		Optional<Income> incomeOptional = incomeRepository.findById(id);
		
		if(incomeOptional.isPresent()) {
			
			Income entityIncome = incomeOptional.get();
			IncomeDTO dto = new IncomeDTO();
			
			BeanUtils.copyProperties(entityIncome, dto);
			
			//Return Optional<DTO> using 'of'
			return Optional.of(dto);
		}
		
		//Else return Empty if not Found
		return Optional.empty();
	}

	@Override
	public List<IncomeDTO> getAllIncomes() {
		// TODO Auto-generated method stub
		
		List<Income> list = incomeRepository.findAll();
		List<IncomeDTO> result = new ArrayList<>();
		
		for(Income income : list) {
			
			IncomeDTO dto = new IncomeDTO();
			
			BeanUtils.copyProperties(income, dto);
			
			result.add(dto);
		}
		
		return result;
	}

	@Override
	public Double getTotalIncome() {
		// TODO Auto-generated method stub
		
		Double totalIncome = 0.0;
		
		for(IncomeDTO dto : getAllIncomes()) {
			
			totalIncome += dto.getAmount();
		}
		
		return totalIncome;
	}

	@Override
	public IncomeDTO updateIncome(String id, IncomeDTO incomeDTO) {
		// TODO Auto-generated method stub
		Optional<Income> existingIncome = incomeRepository.findById(id);
		
		if(existingIncome.isPresent()) {
			
			Income entityIncome = existingIncome.get();
			
			// Update the fields from DTO
			entityIncome.setTitle(incomeDTO.getTitle());
			entityIncome.setAmount(incomeDTO.getAmount());
			entityIncome.setCategory(incomeDTO.getCategory());
			entityIncome.setDate(incomeDTO.getDate());
			entityIncome.setDescription(incomeDTO.getDescription());
			entityIncome.setUserId(incomeDTO.getUserId());
	        
	        //Saving the updated entity
	        Income updatedIncome = incomeRepository.save(entityIncome);
	        
	        //Convert updated entity back to DTO
	        IncomeDTO updatedDTO = new IncomeDTO();
	        BeanUtils.copyProperties(updatedIncome, updatedDTO);
	        
	        //Return updated DTO
	        return updatedDTO;
		}
		
		throw new RuntimeException("Expense With ID : " + id + " Not found...");
	}

	@Override
	public void removeIncomeById(String id) {
		// TODO Auto-generated method stub
		if (!incomeRepository.existsById(id)) {
			
		    throw new RuntimeException("Income with ID " + id + " not found.");
		}
		
		incomeRepository.deleteById(id);
	}

	@Override
	public void removeAllIncomes() {
		// TODO Auto-generated method stub

		incomeRepository.deleteAll();
	}

}
