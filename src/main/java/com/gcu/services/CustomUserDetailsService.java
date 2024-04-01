package com.gcu.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.models.UserModel;
import com.gcu.repositories.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
        	//System.out.println("USER NOT FOUND");
            throw new UsernameNotFoundException("User not found");
        }

        //System.out.println("User found: " + user.getUsername() + " with role: " + user.getRole());
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}