package com.gcu.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gcu.services.UserTaskService;

@Controller
public class TaskController {

    @Autowired
    private UserTaskService userTaskService;
    
    @GetMapping("/tasks/complete/{taskId}")
    public String completeTask(@PathVariable("taskId") Long taskId, Principal principal) {
        String username = principal.getName(); 
        userTaskService.markTaskAsCompleted(taskId, username);
        return "redirect:/user/dashboard"; 
    }
}
