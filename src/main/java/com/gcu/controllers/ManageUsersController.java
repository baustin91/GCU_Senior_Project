package com.gcu.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcu.dto.EditUserDTO;
import com.gcu.models.UserModel;
import com.gcu.services.UserService;

@Controller
@RequestMapping("/admin/manageusers")
public class ManageUsersController {
	

	@Autowired
	UserService userService;
	
    @GetMapping()
    public String manageUsers(Model model) {
        model.addAttribute("title", "Manage Users");
        model.addAttribute("description", "Admin Manage Users Page");
    	model.addAttribute("navbarText", "Admin");
        model.addAttribute("users", userService.getAllUsers()); 
    	return "adminManageUsers";
    }
    
    @GetMapping("/user/{userId}")
    @ResponseBody
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return userService.findByID(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute EditUserDTO userDTO) {
        Optional<UserModel> userOptional = userService.findByID(userDTO.getId());
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            user.setUsername(userDTO.getUsername());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());

            userService.save(user);
        } else {
        	return "redirect:/admin/manageusers"; //fix this else statement 
        }
        return "redirect:/admin/manageusers";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/manageusers";
    }
}
