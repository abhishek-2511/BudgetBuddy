package fi.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsersDTO {
	
	@NotBlank
    @Size(min = 3, max = 50)
    private String username;
	
    @NotBlank
    @Size(max = 20)
    @Email
    private String email;
    
    
    @NotBlank
    @Size(min = 6, max = 120)
    private String password;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
