package com.expense.tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.model.Income;
import com.expense.tracker.repository.IncomeRepository;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    
    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }
    
    public List<Income> getIncomeByUser(String userId) {
        return incomeRepository.findByUserId(userId);
    }
    
    public Optional<Income> getIncomeById(String id) {
        return incomeRepository.findById(id); 
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }
    
	public Double getTotalIncome() {
		Double totalIncome = 0.0;
		for (Income income : getAllIncomes()) {
			totalIncome += income.getAmount();
		}
		return totalIncome;
	}

    public Income updateIncome(String id, Income income) {
    	Optional<Income> existsIncome = incomeRepository.findById(id);
    	if(existsIncome.isPresent()) {
    		existsIncome.get().setTitle(income.getTitle());
    		existsIncome.get().setAmount(income.getAmount());
    		existsIncome.get().setCategory(income.getCategory());
    		existsIncome.get().setDate(income.getDate());
    		existsIncome.get().setDescription(income.getDescription());
    		incomeRepository.save(existsIncome.get());
    		return existsIncome.get();
    	}
    	return null;
    }
    
    public void removeIncome(String id) {
    	incomeRepository.deleteById(id);
    }
    
    public void removeAllIncomes() {
    	incomeRepository.deleteAll();
    }
    
    
}
