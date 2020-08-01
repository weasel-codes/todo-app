package co.nitin.todo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Unique combination of user_id and role
 * @author weasel
 *
 */
@Entity(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private User user;
	private String role;
	
	public UserRole(int id, User user, String role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}
	
	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", user=" + user + ", role=" + role + "]";
	}
}
