package com.gcu.dto;

public class UserProgressDTO {

    private Long completed;
    private Long total;
    
	public UserProgressDTO(Long completed, Long total) {
		super();
		this.completed = completed;
		this.total = total;
	}

	public UserProgressDTO() {
		super();
	}

	public Long getCompleted() {
		return completed;
	}

	public void setCompleted(Long completed) {
		this.completed = completed;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
    
}
