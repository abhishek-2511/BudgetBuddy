package com.expense.tracker.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.expense.tracker.dto.UserDto;
import com.expense.tracker.model.Users;
import com.expense.tracker.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public ResponseEntity<?> register(Users user) {
		
		Users checkUser = userRepository.findByUsername(user.getUsername());
		if(checkUser != null) {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
		}
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public List<Users> getAllUsers(){
		List<Users> listUsers = userRepository.findAll();
		return listUsers;
	}
	
	public Users getByUsername(String usersname) {
		Users user = userRepository.findByUsername(usersname);
		return user;
	}
	
	public Users getById(int id){
		Users user= userRepository.findById(id).get();
		return user;
	}
	
	public ResponseEntity<?> login(Users user) {
		 Users existUser = userRepository.findByUsername(user.getUsername());

	        if (existUser == null) {
	            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
	        }
	        if (!encoder.matches(user.getPassword(), existUser.getPassword())) {
	            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
	        }
	        encoder.matches(user.getPassword(), existUser.getPassword());
	        return new ResponseEntity<>(existUser, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> forgot(Users user){
		Users existUser =  userRepository.findByUsername(user.getUsername());
		
			UserDto userDto = new UserDto();
			if(existUser == null) {
	            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
			}
			
			existUser.setPassword(encoder.encode(user.getPassword()));
			BeanUtils.copyProperties(existUser, userDto);
			userRepository.save(existUser);
	        return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
}
