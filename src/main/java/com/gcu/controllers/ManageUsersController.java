package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("/users")
    public String listUsers(Model model) {
    	System.out.println("Getting users");
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
