package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "Entry Point");
        model.addAttribute("description", "Index Page");
        model.addAttribute("extraCss", "https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css");
        return "index";
    }
}
