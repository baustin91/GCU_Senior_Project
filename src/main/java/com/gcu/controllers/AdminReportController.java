package com.gcu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.dto.TaskReportDTO;
import com.gcu.services.TaskReportService;

@Controller
public class AdminReportController {
	
	@Autowired
	TaskReportService taskReportService;
	
    @GetMapping("/admin/reports")
    public String reports(Model model) {
        model.addAttribute("title", "Reports");
        model.addAttribute("description", "Admin Reports Page");
    	model.addAttribute("navbarText", "Admin");
	    List<TaskReportDTO> reports = taskReportService.getUserTaskReports();
	    model.addAttribute("reports", reports);
    	return "adminReports";
    }
}
