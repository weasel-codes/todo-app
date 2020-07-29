package co.nitin.todo.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "task")
public class Task {

	@Id
	private Long id;
	
	private String name;
	private String details;
	
	@Column(name = "created_at")
	private Date createdAt;

	@OneToOne
	private User user;

	@OneToOne
	private TaskList taskList;
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public User getUserId() {
		return user;
	}
	public void setUserId(User userId) {
		this.user = userId;
	}
	public TaskList getTaskList() {
		return taskList;
	}
	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}
}
