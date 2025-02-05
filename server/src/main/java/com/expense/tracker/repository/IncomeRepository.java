package com.expense.tracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.expense.tracker.model.Income;

@Repository
public interface IncomeRepository extends MongoRepository<Income, String> {

}


