package co.nitin.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.nitin.todo.dao.repo.UserRepo;
import co.nitin.todo.dao.repo.UserRoleRepo;
import co.nitin.todo.model.entity.User;
import co.nitin.todo.model.req.BaseRequest;
import co.nitin.todo.model.req.UserSignup;

@Service
public class UserService {

	private UserRepo userRepo;
	private UserRoleRepo userRoleRepo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepo userRepo, UserRoleRepo userRoleRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.userRoleRepo = userRoleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public boolean createUser(BaseRequest<UserSignup> req) {

		String pass = passwordEncoder.encode(req.getRequest().getPassword());
		User newUser = new User(req.getRequest().getName(), req.getRequest().getUsername(), pass, req.getRequest().getEmail(), req.getRequest().getMobile());;
		return false;
	}
}
