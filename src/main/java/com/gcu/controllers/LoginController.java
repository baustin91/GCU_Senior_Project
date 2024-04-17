package com.gcu.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	
    @GetMapping()
    public String loginPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        model.addAttribute("title", "Login");
        model.addAttribute("description", "Login Page");
        return "login";
    }
    
    @GetMapping("/defaultRedirect")
    public String defaultRedirect(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                System.out.println("ADMIN SUCCESS");
                return "redirect:/admin/dashboard";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
                System.out.println("USER SUCCESS");
                return "redirect:/user/dashboard";
            }
        }
        System.out.println("FAILED");
        return "redirect:/";
    }
    
}
