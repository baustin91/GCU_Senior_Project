package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project-portfolio")
public class PortfolioController {

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "Project Portfolio");
        model.addAttribute("description", "Project Portfolio");
        return "projectPortfolio";
    }
}
