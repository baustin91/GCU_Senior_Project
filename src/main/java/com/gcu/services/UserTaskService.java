package com.gcu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.dto.UserProgressDTO;
import com.gcu.models.UserTasks;
import com.gcu.repositories.UserTaskRepository;

@Service
public class UserTaskService {
	
    @Autowired
    private UserTaskRepository userTaskRepository;
    
    public void markTaskAsCompleted(Long taskId, String username) {
        UserTasks userTask = userTaskRepository.findByTaskIdAndUsername(taskId, username);
        if (userTask != null) {
            userTask.setCompleted(true);
            userTaskRepository.save(userTask);
        }
    }
    
    public UserProgressDTO getUserProgress(String username) {
        Long totalTasks = userTaskRepository.countByUser(username);
        Long completedTasks = userTaskRepository.countCompletedByUser(username);
        
        return new UserProgressDTO(completedTasks, totalTasks);
    }

}
