package fi.backend.services;

import java.util.List;
import java.util.Optional;

import fi.backend.dto.ExpenseDTO;
import fi.backend.entity.Expense;

public interface ExpenseService {
	
	Expense addExpense(ExpenseDTO expenseDTO);

    List<ExpenseDTO> getExpensesByUserId(String userId);

    Optional<ExpenseDTO> getExpenseById(String id);

    List<ExpenseDTO> getAllExpenses();

    Double getTotalExpenses();

    ExpenseDTO updateExpense(String id, ExpenseDTO expenseDTO);

    void removeExpense(String id);

    void removeAllExpenses();
}
