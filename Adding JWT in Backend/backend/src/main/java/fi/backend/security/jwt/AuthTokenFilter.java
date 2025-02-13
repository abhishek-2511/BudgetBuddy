package fi.backend.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import fi.backend.security.services.UserDetailsServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Filter to validate the JWT token and set user authentication in the security context.
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JWTutils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImplementation userDetailsService;
	
	// Logger for logging errors
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class); 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			//Parse and validate the JWT token coming from the request
			String jwt = parseJWT(request);
			
			if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
				
				//Get username from the Token
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				
				//Load userDetails from the username
				UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
				
				//Create an authentication token with the user details
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				// Set additional details from the request
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//Set the authentication in the security context
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
		}
		catch(Exception e) {
			
			logger.error( "Cannot set user authentication: {}" + e);
		}
		
		//Continue the Filter Chain
		filterChain.doFilter(request, response);
	}
	
	private String parseJWT(HttpServletRequest request) {
		
		//Get the Authorization header from the request
		String headerAuth = request.getHeader("Authorization");
		
		//Check if the header is valid and start with Bearer
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			
			//Extract the JWT Token from the Header
			return headerAuth.substring(7);	//After "Bearer (token)"
		}
		
		//else return null if no token is found
		return null;
	}

}
