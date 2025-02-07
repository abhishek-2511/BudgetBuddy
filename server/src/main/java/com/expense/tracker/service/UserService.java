package com.expense.tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.expense.tracker.model.Users;
import com.expense.tracker.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
//	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public ResponseEntity<?> register(Users user) {
//		
//		Users checkUser = userRepository.findByUsername(user.getUsername());
//		if(checkUser != null) {
//            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
//		}
////		user.setPassword(encoder.encode(user.getPassword()));
//		user.setPassword(user.getPassword());
//		userRepository.save(user);
		
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user); 
        return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> login(Users user) {
//		Users existUser = userRepository.findByUsername(user.getUsername());
////		 System.out.println(user.toString() + " " + existUser.toString());
//		
//		if (existUser == null) { 
//			System.out.println("User Not Found");
//			return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
//		}
////	        if (!encoder.matches(user.getPassword(), existUser.getPassword())) {
//		if (!user.getPassword().equals(existUser.getPassword())) {
//			System.out.println("Invalid credentials");
//			return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
//		}
////	        encoder.matches(user.getPassword(), existUser.getPassword());
//		if(user.getPassword() == existUser.getPassword());{
//			System.out.println(existUser.toString());
//			return new ResponseEntity<>(existUser, HttpStatus.OK);
			
	        Optional<Users>  existUser = userRepository.findByUsername(user.getUsername());
	        if(existUser.isEmpty())	
	        	existUser = userRepository.findByEmail(user.getEmail());
	        if (existUser.isPresent() && existUser.get().getPassword().equals(user.getPassword())) {
			return new ResponseEntity<>(existUser, HttpStatus.OK);
	        } else {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
			
		}
	}
	
	public ResponseEntity<?> forgotPassword(Users user){
//		Users existUser =  userRepository.findByUsername(user.getUsername());
//		
//			UserDto userDto = new UserDto();
//			if(existUser == null) {
//	            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
//			}
//			
////			existUser.setPassword(encoder.encode(user.getPassword()));
//			existUser.setPassword((user.getPassword()));
//			BeanUtils.copyProperties(existUser, userDto);
//			userRepository.save(existUser);
//	        return new ResponseEntity<>(userDto, HttpStatus.OK);
        Optional<Users>  existUser = userRepository.findByUsername(user.getUsername());
        if(existUser.isEmpty())	
        	existUser = userRepository.findByEmail(user.getEmail());
        if (existUser.isPresent()) {
            String newPassword = user.getPassword();
            existUser.get().setPassword(newPassword);
            userRepository.save(existUser.get());
	        return new ResponseEntity<>(existUser, HttpStatus.OK);
        } else {
	            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
        }

	}

	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Users getByUsername(String usersname) {
		Users user = userRepository.findByUsername(usersname).get();
		return user;
	}
	
	public Users getUserById(String id){
		Users user = userRepository.findById(id).get();
		return user;
	}

}


//<dependency>
//<groupId>org.springframework.boot</groupId>
//<artifactId>spring-boot-starter-security</artifactId>
//</dependency>

//<dependency>
//<groupId>org.springframework.security</groupId>
//<artifactId>spring-security-test</artifactId>
//<scope>test</scope>
//</dependency>
