package com.expense.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.expense.tracker.model.Income;
import com.expense.tracker.repository.IncomeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    
    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }
    
    public Optional<Income> getIncomeById(String id) {
        return incomeRepository.findById(id); 
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income updateIncome(Income income) {
    	return incomeRepository.save(income);
    }
    
    public void removeIncome(String id) {
    	incomeRepository.deleteById(id);
    }
    
    public void removeAllIncomes() {
    	incomeRepository.deleteAll();
    }
    
    
}
