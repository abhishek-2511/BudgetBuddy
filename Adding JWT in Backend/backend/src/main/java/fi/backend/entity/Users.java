package fi.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection="users")
public class Users {
	
	@Id
	private String id;
	
	@NotBlank
	@Size(max = 50)
	private String username;
	
	@NotBlank
	@Size(max = 120)
	private String password;
	
	@NotBlank
	@Size(max = 20)
	private String email;
	
	public Users(String username, String password, String email) {

		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
