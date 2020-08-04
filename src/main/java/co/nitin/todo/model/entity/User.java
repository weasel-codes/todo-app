package co.nitin.todo.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;
	
	private String name;

	private String username;
	
	private String password;
	
	private String email;
	
	private Long mobile;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	public User() { //needed for orm and injection
		super();
	}

	public User(String name, String username, String password, String email, Long mobile) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
	}

	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Long getMobile() {
		return mobile;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", mobile=" + mobile + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
}
