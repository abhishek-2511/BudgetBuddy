package fi.backend.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.backend.entity.Users;
import fi.backend.repositories.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional	//Ensures that the method is transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = userRepository.findByUsername(username);
					 
		if(user == null) {
			throw new UsernameNotFoundException("User not found with username : " + username);
		}
		
		// Convert Users entity to UserDetailsImplementation
		return UserDetailsImplementation.build(user);
	}
	
}
