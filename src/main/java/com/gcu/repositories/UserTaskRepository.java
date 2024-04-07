package com.gcu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gcu.models.UserTasks;

public interface UserTaskRepository extends JpaRepository<UserTasks, Long> {

    @Query("SELECT ut FROM UserTasks ut WHERE ut.taskId = :taskId AND ut.user.username = :username")
    UserTasks findByTaskIdAndUsername(@Param("taskId") Long taskId, @Param("username") String username);
    
    @Query("SELECT COUNT(ut) FROM UserTasks ut JOIN ut.user u WHERE u.username = :username")
    Long countByUser(@Param("username") String username);

    @Query("SELECT COUNT(ut) FROM UserTasks ut JOIN ut.user u WHERE u.username = :username AND ut.isCompleted = true")
    Long countCompletedByUser(@Param("username") String username);
    
    List<UserTasks> findByUserId(Long userId);

}
