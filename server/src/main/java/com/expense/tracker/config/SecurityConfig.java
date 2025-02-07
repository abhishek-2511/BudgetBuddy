//package com.expense.tracker.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())  // Disable CSRF if not needed
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/user/register").permitAll()  // Allow registration without authentication
//                .anyRequest().authenticated()  // Require authentication for all other routes
//            )
//            .httpBasic(httpBasic -> {});  // Enable Basic Authentication
//
//        return http.build();
//    }
//}
