package co.nitin.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.nitin.todo.constants.RoleConstants;
import co.nitin.todo.dao.repo.UserRepo;
import co.nitin.todo.dao.repo.UserRoleRepo;
import co.nitin.todo.model.entity.User;
import co.nitin.todo.model.entity.UserRole;
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
	
	@Transactional
	public boolean createUser(BaseRequest<UserSignup> req) {

		String encodedPassword = this.passwordEncoder.encode(req.getRequest().getPassword());
		User newUser = new User(req.getRequest().getName(), req.getRequest().getUsername(), 
								encodedPassword, req.getRequest().getEmail(), 
								req.getRequest().getMobile());
		
		newUser = this.userRepo.save((newUser));
		UserRole role = new UserRole(newUser, RoleConstants.ROLE_USER);
		role = this.userRoleRepo.save(role);

		return true;
	}
}
