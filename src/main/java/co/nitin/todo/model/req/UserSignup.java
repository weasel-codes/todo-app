package co.nitin.todo.model.req;

public class UserSignup {

	private String name;
	private String username;
	private String password;
	private String email;
	private Long mobile;
	
	public UserSignup(String name, String username, String password, String email, Long mobile) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
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

	@Override
	public String toString() {
		return "UserSignup [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", mobile=" + mobile + "]";
	}
}
