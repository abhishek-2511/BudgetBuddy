package com.expense.tracker.config;  // Ensure the package declaration matches your directory structure

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  
            .authorizeRequests()
                .requestMatchers("/user/register").permitAll()  
                .anyRequest().authenticated()  
            .and()
            .httpBasic();  
        return http.build();
    }
}
