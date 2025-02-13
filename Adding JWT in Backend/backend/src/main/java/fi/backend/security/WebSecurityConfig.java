package fi.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fi.backend.security.jwt.AuthEntryPointJwt;
import fi.backend.security.jwt.AuthTokenFilter;
import fi.backend.security.services.UserDetailsServiceImplementation;

@Configuration // Marks the class as a source of bean definitions
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsServiceImplementation userDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unAuthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJWTtokenFilter() {
		
		//returns a new instance of AuthTokenFilter
		return new AuthTokenFilter();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		//Create a new Authentication Provider
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		//Setting userDetailService and Password Encoder
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		// Return the configured authentication provider
		return authProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
		
		//Returns the authentication manager from the configuration
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder(); // Returns a new instance of BCryptPasswordEncoder
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		// Configure CSRF protection, exception handling, session management, and authorization
		http.csrf(AbstractHttpConfigurer::disable)	// Disable CSRF protection
		
				// Set unauthorized handler
				.exceptionHandling(exception -> exception.authenticationEntryPoint(unAuthorizedHandler))	
				
				// Set session policy to stateless
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				
				// Configure authorization for HTTP requests
				.authorizeHttpRequests(auth -> auth	
						
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/test/**").permitAll()
						.anyRequest().authenticated()
				);
				
		//Require authentication for any other request
		http.authenticationProvider(authenticationProvider());
		
		// Add the JWT token filter before the username/password authentication filter
		http.addFilterBefore(authenticationJWTtokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// Build and return the security filter chain
		return http.build();
	}
}
