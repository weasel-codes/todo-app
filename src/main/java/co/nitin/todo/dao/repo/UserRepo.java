package co.nitin.todo.dao.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.nitin.todo.model.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);
	public List<User> findByMobile(Long mobile);
	public List<User> findByEmail(String email);
}
