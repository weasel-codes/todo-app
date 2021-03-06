package co.nitin.todo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.nitin.todo.dao.repo.UserRepo;
import co.nitin.todo.dao.repo.UserRoleRepo;
import co.nitin.todo.model.SecurityUserDetails;
import co.nitin.todo.model.entity.User;

@Service
public class SecurityUserDetailsService implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

	private UserRepo userRepo;
	private UserRoleRepo userRoleRepo;
	
	public SecurityUserDetailsService() {
		super();
	}

	@Autowired
	public SecurityUserDetailsService(UserRepo userRepo, UserRoleRepo userRoleRepo) {
		super();
		logger.info("[SecurityUserDetailsService] : constructor for setting user repo and userrolerepo");
		this.userRepo = userRepo;
		this.userRoleRepo = userRoleRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info("[loadUserByUsername] : returns UserDetails from User and UserRoles");
		
		User user = this.userRepo.findByUsername(username);
		if(user==null) throw new UsernameNotFoundException("User not registered");			
		List<String> roles = this.userRoleRepo.findRoleByUserMobile(user.getMobile());
		logger.info("[loadUserByUsername] : User" + user);	
		logger.info("[loadUserByUsername] : UserRoles" + roles);

		return new SecurityUserDetails(user, roles);
	}

}
