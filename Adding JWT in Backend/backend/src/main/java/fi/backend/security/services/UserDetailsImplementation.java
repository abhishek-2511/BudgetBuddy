package fi.backend.security.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fi.backend.entity.Users;

public class UserDetailsImplementation implements UserDetails {
	
	private static final long serialVersionUID = 1L; // Serializable version identifier
	
	private String id;
	private String username;
	private String password;
	private String email;
	
	public UserDetailsImplementation(String id, String username, String password, String email) {

		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	//Builds a UserDetailsImplementation instance from a user
	public static UserDetailsImplementation build(Users user) {
		
		return new UserDetailsImplementation(
			user.getId(),
			user.getUsername(),
			user.getPassword(),
			user.getEmail()
		);
	}
	
	public String getId() {
		return id; // Return user ID
	}
	
	public String getEmail() {
		return email; // Return email
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true; // FIXED: Required for Spring Security
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // FIXED: Required for Spring Security
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // FIXED: Required for Spring Security
    }

    @Override
    public boolean isEnabled() {
        return true; // FIXED: Required for Spring Security
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) // Check if the same object
			return true;
		if (o == null || getClass() != o.getClass()) // Check if the object is null or not of the same class
			return false;
		UserDetailsImplementation user = (UserDetailsImplementation) o; // Cast to UserDetailsImpl
		return id == user.id; // Check if IDs are equal
	}

}
