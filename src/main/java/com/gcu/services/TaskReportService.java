package com.gcu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.dto.TaskReportDTO;
import com.gcu.models.UserModel;
import com.gcu.models.UserTasks;
import com.gcu.repositories.UserRepository;
import com.gcu.repositories.UserTaskRepository;

@Service
public class TaskReportService {
	
    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private UserRepository userRepository; 

    public List<TaskReportDTO> getUserTaskReports() {
        List<TaskReportDTO> reports = new ArrayList<>();
        
        
        List<UserModel> users = userRepository.findAll();
        
        for (UserModel user : users) {
            TaskReportDTO report = new TaskReportDTO();
            report.setUsername(user.getUsername());
            report.setName(user.getFirstName() + " " + user.getLastName()); 
            
            List<UserTasks> tasks = userTaskRepository.findByUserId(user.getId());

            boolean taskOneCompleted = false;
            boolean taskTwoCompleted = false;
            boolean taskThreeCompleted = false;

            for (UserTasks task : tasks) {
                if (task.getTaskId() == 1) {
                    taskOneCompleted = task.isCompleted();
                } else if (task.getTaskId() == 2) {
                    taskTwoCompleted = task.isCompleted();
                } else if (task.getTaskId() == 3) {
                    taskThreeCompleted = task.isCompleted();
                }
            }

            report.setTaskOneCompleted(taskOneCompleted);
            report.setTaskTwoCompleted(taskTwoCompleted);
            report.setTaskThreeCompleted(taskThreeCompleted);

            reports.add(report);
        }
        
        return reports;
    }

}
