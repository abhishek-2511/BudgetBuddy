//package com.expense.tracker.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.expense.tracker.model.Users;
//import com.expense.tracker.repository.UserRepository;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//    	Users user = userRepository.findByUsername(username);
////        
//        if (user == null) {
//            System.out.println("User Not Found");
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        return User.builder()
//                   .username(user.getUsername())
//                   .password(user.getPassword())  
//                   .build();
//    }
//}
