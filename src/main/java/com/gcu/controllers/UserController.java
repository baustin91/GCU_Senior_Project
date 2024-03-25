package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("title", "User Dashboard");
        model.addAttribute("description", "User Dashboard");
        model.addAttribute("extraCss", "https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css");
        model.addAttribute("navbarText", "User");
        return "userDashboard";
    }
    
    @GetMapping("/forms")
    public String forms(Model model) {
        model.addAttribute("title", "Forms");
        model.addAttribute("description", "Forms Page");
        model.addAttribute("navbarText", "User");
        return "userForms";
    }
    
    @GetMapping("/videos")
    public String videos(Model model) {
        model.addAttribute("title", "Videos");
        model.addAttribute("description", "Videos Page");
        model.addAttribute("navbarText", "User");
        return "userVideos";
    }
    
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("title", "Test");
        model.addAttribute("description", "Test Page");
        model.addAttribute("navbarText", "User");
        return "userTest";
    }
}
