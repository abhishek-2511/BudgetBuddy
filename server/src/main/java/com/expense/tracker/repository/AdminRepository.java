package com.expense.tracker.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.expense.tracker.model.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
	
	Optional<Admin> findByUsername(String username);
	Optional<Admin> findByEmail(String email);

}
