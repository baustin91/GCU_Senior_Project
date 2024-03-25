package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @GetMapping()
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("description", "Registration Page");
        return "register";
    }
}
