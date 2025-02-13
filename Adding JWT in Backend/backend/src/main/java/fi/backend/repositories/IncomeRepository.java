package fi.backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fi.backend.entity.Income;

@Repository
public interface IncomeRepository extends MongoRepository<Income, String> {
	
	List<Income> findByUserId(String userId);
}
