package co.nitin.todo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Unique combination of user_id and role
 * @author weasel
 *
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private User user;

	private String role;
	
	public UserRole() {
		super();
	}

	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}
	
	public Integer getId() {
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
