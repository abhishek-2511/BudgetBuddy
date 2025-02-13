package fi.backend.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fi.backend.entity.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, Integer> {
	
	//Find a user by their username
	Users findByUsername(String username);
	
	//Check if username already exist
	Boolean existsByUsername(String username);
	
	//Check if an email already exist
	Boolean existsByEmail(String email);
}
