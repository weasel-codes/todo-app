package co.nitin.todo.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String details;
	
	@OneToOne
	private User user;

	@OneToOne
	private TaskList taskList;
		
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	public Task() { //needed for orm and injection
		super();
	}
	
	public Task(Long id, String name, String details, User user, TaskList taskList) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.user = user;
		this.taskList = taskList;
	}

	public Task(String name, String details, User user, TaskList taskList) {
		super();
		this.name = name;
		this.details = details;
		this.user = user;
		this.taskList = taskList;
	}

	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}

	public String getName() {
		return name;
	}

	public String getDetails() {
		return details;
	}

	public User getUser() {
		return user;
	}

	public TaskList getTaskList() {
		return taskList;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
