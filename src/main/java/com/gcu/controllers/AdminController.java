package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("title", "Admin Dashboard");
        model.addAttribute("description", "Admin Dashboard");
        model.addAttribute("navbarText", "Admin");
        return "adminDashboard";
    }
    
    
    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("title", "Reports");
        model.addAttribute("description", "Admin Reports Page");
    	model.addAttribute("navbarText", "Admin");
    	return "adminReports";
    }
	
}

