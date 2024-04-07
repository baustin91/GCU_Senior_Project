package com.gcu.dto;

public class TaskReportDTO {

    private String username;
    private String name; 
    private boolean taskOneCompleted;
    private boolean taskTwoCompleted;
    private boolean taskThreeCompleted;
    
	public TaskReportDTO(String username, String name, boolean taskOneCompleted, boolean taskTwoCompleted,
			boolean taskThreeCompleted) {
		super();
		this.username = username;
		this.name = name;
		this.taskOneCompleted = taskOneCompleted;
		this.taskTwoCompleted = taskTwoCompleted;
		this.taskThreeCompleted = taskThreeCompleted;
	}

	public TaskReportDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTaskOneCompleted() {
		return taskOneCompleted;
	}

	public void setTaskOneCompleted(boolean taskOneCompleted) {
		this.taskOneCompleted = taskOneCompleted;
	}

	public boolean isTaskTwoCompleted() {
		return taskTwoCompleted;
	}

	public void setTaskTwoCompleted(boolean taskTwoCompleted) {
		this.taskTwoCompleted = taskTwoCompleted;
	}

	public boolean isTaskThreeCompleted() {
		return taskThreeCompleted;
	}

	public void setTaskThreeCompleted(boolean taskThreeCompleted) {
		this.taskThreeCompleted = taskThreeCompleted;
	}
    
}
