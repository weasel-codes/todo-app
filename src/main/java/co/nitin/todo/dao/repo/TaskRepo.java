package co.nitin.todo.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.nitin.todo.model.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{

	@Query("Select t from Task t, User u where t.user.userId=u.userId AND u.mobile=?1")
	public List<Task> findByUserMobile(Long mobile);	
}
