package fi.backend.services;

import java.util.List;
import java.util.Optional;

import fi.backend.dto.IncomeDTO;

public interface IncomeService {
	
	IncomeDTO addIncome(IncomeDTO incomeDTO);
	
	List<IncomeDTO> getIncomeByUserId(String userId);
	
	Optional<IncomeDTO> getIncomeById(String id);
	
	List<IncomeDTO> getAllIncomes();
	
	Double getTotalIncome();
	
	IncomeDTO updateIncome(String id, IncomeDTO incomeDTO);
	
	void removeIncomeById(String id);
	
	void removeAllIncomes();
}
