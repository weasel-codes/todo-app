package co.nitin.todo.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.nitin.todo.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
}
