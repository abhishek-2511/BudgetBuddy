package fi.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fi.backend.entity.Expense;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
	
	List<Expense> findByUserId(String userId);
}
