package com.expense.tracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.expense.tracker.model.Expense;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {

}
