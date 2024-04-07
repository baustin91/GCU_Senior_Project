package com.gcu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.models.UserModel;
import com.gcu.models.UserTasks;
import com.gcu.repositories.UserRepository;
import com.gcu.repositories.UserTaskRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserTaskRepository userTaskRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserTaskRepository userTaskRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userTaskRepository = userTaskRepository;
    }

    public UserModel registerNewUser(String username, String password, String firstName, String lastName, String role) {
        UserModel newUser = new UserModel();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); 
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setRole(role);
        newUser.setEnabled(true); 
        
        UserModel savedUser = userRepository.save(newUser);

        addDefaultTasksForUser(savedUser.getId(), new long[]{1, 2, 3});

        return savedUser;
    }
    
    private void addDefaultTasksForUser(Long userId, long[] taskIds) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        for (long taskId : taskIds) {
            UserTasks newUserTask = new UserTasks();
            newUserTask.setUser(user);
            newUserTask.setTaskId(taskId);
            newUserTask.setCompleted(false);
            userTaskRepository.save(newUserTask);
        }
    }
    
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<UserModel> findByID(Long id) {
    	return userRepository.findById(id);
    }
    
    @Transactional
    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }
    
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
}
