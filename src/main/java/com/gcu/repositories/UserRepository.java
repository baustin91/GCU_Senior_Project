package com.gcu.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    
	UserModel findByUsername(String username);
    
    List<UserModel> findAll();
    List<UserModel> findByRole(String role);
    boolean existsByUsername(String username);


}
