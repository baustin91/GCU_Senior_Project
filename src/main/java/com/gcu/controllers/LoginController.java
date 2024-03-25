package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
    @GetMapping()
    public String loginPage(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("description", "Login Page");
        return "login";
    }
}
