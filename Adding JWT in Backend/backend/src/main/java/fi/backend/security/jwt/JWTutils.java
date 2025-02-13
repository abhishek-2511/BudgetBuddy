package fi.backend.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import fi.backend.security.services.UserDetailsImplementation;
import io.jsonwebtoken.*;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

// Utility class for managing JWT
@Component
public class JWTutils {

    // Logger for logging errors
    private static final Logger logger = LoggerFactory.getLogger(JWTutils.class);

    @Value("${jwtSecret}") // Injecting JWT-Secret from application.properties
    private String jwtSecret;

    @Value("${jwtExpirationMs}") // Injecting JWT expiration time from application.properties
    private int jwtExpiration;

    /**
     * Generate a JWT token for the authenticated user.
     * @param authentication The authentication object containing user details.
     * @return The generated JWT token as a string.
     */
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImplementation userPrinciple = (UserDetailsImplementation) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrinciple.getUsername()) // Set username as subject
                .setIssuedAt(new Date()) // Set current time as issued time
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration)) // Set expiration time
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign with secret key
                .compact();
    }

    /**
     * Extract the username from the given JWT token.
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String getUserNameFromJwtToken(String token) {
        
    	return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Set the signing key
                .build()
                .parseClaimsJws(token) // Parse the token
                .getBody()
                .getSubject(); // Extract the subject (username)
    }

    /**
     * Validate the given JWT token.
     * @param authToken The JWT token to validate.
     * @return True if the token is valid, false otherwise.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Set the signing key
                .build()
                .parseClaimsJws(authToken); // Parse the token to validate it
            return true;
        } 
        catch (MalformedJwtException e) {
            
        	logger.error("Invalid JWT token: {}", e.getMessage());
        } 
        catch (ExpiredJwtException e) {
            
        	logger.error("JWT token is expired: {}", e.getMessage());
        } 
        catch (UnsupportedJwtException e) {
            
        	logger.error("JWT token is unsupported: {}", e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            
        	logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;	//Else token is Invalid
    }

    /**
     * Convert the JWT secret key from a Base64-encoded string to a Key object.
     * @return The signing key.
     */
    
    private Key getSigningKey() {
    	
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
