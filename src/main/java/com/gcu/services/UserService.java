package com.gcu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.models.UserModel;
import com.gcu.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel registerNewUser(String username, String password, String firstName, String lastName, String role) {
        UserModel newUser = new UserModel();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); 
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setRole(role);
        newUser.setEnabled(true); 

        return userRepository.save(newUser);
    }
    
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}
