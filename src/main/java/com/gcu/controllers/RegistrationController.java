package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.models.UserModel;
import com.gcu.services.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    private final UserService userService;
    
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("description", "Registration Page");
        model.addAttribute("user", new UserModel());
        return "register";
    }
    
    
    @PostMapping
    public String registerUser(@ModelAttribute UserModel user, RedirectAttributes redirectAttributes) {
        boolean success = userService.registerNewUser(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), "ROLE_USER");
        if (!success) {
            redirectAttributes.addFlashAttribute("error", "Username already exists.");
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
