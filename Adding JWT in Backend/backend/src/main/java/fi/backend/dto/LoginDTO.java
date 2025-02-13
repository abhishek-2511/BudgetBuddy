package fi.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
	
	@NotBlank
	String username;
	
	@NotBlank
	String Password;
	
	public LoginDTO(String username, String password) {

		this.username = username;
		this.Password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
