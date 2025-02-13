package fi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fi.backend.dto.JWTResponseDTO;
import fi.backend.dto.LoginDTO;
import fi.backend.dto.MessageResponse;
import fi.backend.dto.UsersDTO;
import fi.backend.entity.Users;
import fi.backend.repositories.UserRepository;
import fi.backend.security.jwt.JWTutils;
import fi.backend.security.services.UserDetailsImplementation;
import jakarta.validation.Valid;

@CrossOrigin(origins= "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;	// Handles user authentication
	
	@Autowired
	UserRepository userRepository;	// Repository for user-related database operations
	
	@Autowired
	PasswordEncoder encoder;	// Encoder for password hashing
	
	@Autowired
	JWTutils jwtUtils;	// Utility for generating JWT tokens
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO dto){
		
		//Authenticate the user with provided username and password
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		
		//Set the authentication in the security context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//Generate JWT token based on the authentication
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		//Get user Details from the authentication object
		UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
		
		//Return a response containing the JWT and user Details
		return ResponseEntity.ok(new JWTResponseDTO(
				jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail()
		));
	}
	
	//Register a new User Account
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UsersDTO dto){
		
		//Check if username is already taken
		if(userRepository.existsByUsername(dto.getUsername())) {
			
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is Already Taken..."));
		}
		
		//Check if email is Already Taken
		if(userRepository.existsByEmail(dto.getEmail())) {
			
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use..."));
		}
		
		//Else Create a new User Account
		Users user = new Users(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getEmail());
		
		//Save the user
		userRepository.save(user);
		
		// Return a success message upon successful registration
		return ResponseEntity.ok(new MessageResponse("User Registered SuccessFull....."));
	}
}
